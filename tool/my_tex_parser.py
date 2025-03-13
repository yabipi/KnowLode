import os
from pylatexenc.latexwalker import LatexWalker, LatexNode, LatexEnvironmentNode, LatexMathNode, LatexMacroNode, LatexCharsNode, LatexCommentNode
from typing import List, Dict, Optional
import re

class Question:
    def __init__(self):
        self.title: str = ""
        self.content: str = ""
        self.answer: str = ""
        self.type: str = "unknown"  # choice, fill, essay
        self.options: List[str] = []  # for choice questions
        self.section: str = ""

current_section = None
current_question = None
questions = []
current_question_type = None
options = None
current_title = None
current_task = None
task_content = None

def extract_question_type(section_text: str) -> str:
    """从section文本中提取题目类型"""
    # 使用正则表达式匹配大括号中的内容
    match = re.search(r'\{([^}]+)\}', section_text)
    if match:
        type_text = match.group(1)
        # 根据中文描述判断题目类型
        if "选择" in type_text:
            return "choice"
        elif "填空" in type_text:
            return "fill"
        elif "解答" in type_text or "问答" in type_text:
            return "essay"
    return "unknown"

def parse_node(node: LatexNode, walker: LatexWalker) -> Optional[Question]:
    global questions
    global current_question
    global current_question_type
    global current_title
    global options
    global current_task
    global task_content

    question = None

    # 处理section
    # if isinstance(node, LatexMacroNode) and node.macroname == 'section':
    #     section_text = walker.get_latex_text(pos=node.nodelist.pos, len=node.nodelist.len)
    #     # current_section = section_text
    #     current_question_type = extract_question_type(section_text)

        # if result:
        #     if isinstance(result, list):
        #         # 如果是题目列表
        #         for question in result:
        #             question.section = current_section
        #             questions.append(question)
        #     else:
        #         # 如果是单个题目
        #         result.section = current_section
        #         questions.append(result)

    if isinstance(node, LatexEnvironmentNode) and node.environmentname == 'enumerate':
        pass

    # 处理section节点
    if isinstance(node, LatexMacroNode) and node.macroname == 'section':
        if current_title and len(current_title) > 0:
            current_question_type = extract_question_type(current_title)
            current_title = ''
        # section_text = walker.get_latex_text(pos=node.nodelist.pos, len=node.nodelist.len)

        # current_question =  Question(section=section_text, type=question_type)
        # questions.append(current_question)

    if isinstance(node, LatexCharsNode):
        if not current_title:
            current_title = ''
        current_title += node.chars
        pass

    # 处理item（题目）
    if isinstance(node, LatexMacroNode) and node.macroname == 'item':
        if current_task:
            if options is not None and task_content is not None and len(task_content) > 0:
                options.append(task_content)
        if current_question:
            current_question.title = current_title
            if options and len(options) > 0:
                current_question.options.extend(options)
            # questions.append(current_question)
        current_task = False
        current_title = ''
        current_question = Question()
        current_question.type = current_question_type  # 使用当前section的题目类型
        # current_question.content = walker.get_latex_text(pos=node.nodelist.pos, len=node.nodelist.len)
        questions.append(current_question)

    if isinstance(node, LatexEnvironmentNode) and node.environmentname == 'tasks':
        options = []

    if isinstance(node, LatexMathNode):
        content = ''
        for child in node.nodelist:
            if isinstance(child, LatexCharsNode):
                content += child.chars
            if isinstance(child, LatexMacroNode):
                content += child.macroname
        if current_task:
            task_content += content
        elif current_title:
            current_title += content


    if isinstance(node, LatexMacroNode) and node.macroname == 'task':
        if options is not None and task_content is not None and len(task_content) > 0:
            options.append(task_content)
        current_task = True # 当前处理task中
        task_content = ''
        # option_text = walker.get_latex_text(pos=node.nodelist.pos, len=node.nodelist.len)


    children = []
    if hasattr(node, 'nodeargs'):
        children = node.nodeargs
    elif hasattr(node, 'nodelist'):
        children = node.nodelist

    for child in children:
        # parse_node(child, walker, current_question_type)
        # 处理题目
        parse_node(child, walker)
    # 处理选项（在begin和end之间的\task）
    # if isinstance(node, LatexEnvironmentNode):
    #     if node.environmentname == 'tasks':
    #         options = []
    #         for task_node in child.nodelist:
    #             if isinstance(task_node, LatexMacroNode) and task_node.macroname == 'task':
    #                 option_text = walker.get_latex_text(pos=task_node.nodelist.pos, len=task_node.nodelist.len)
    #                 options.append(option_text)
    #         if current_question:
    #             current_question.options = options

    # 处理enumerate环境（题目列表）
    # if isinstance(node, LatexEnvironmentNode):
    #     if node.environmentname == 'enumerate':
    #         for child in node.nodelist:
    #             pass
    #         # 添加最后一个题目
    #         if current_question:
    #             questions.append(current_question)
    #
    #         return questions
            
    # return None

def extract_questions_from_tex_file(file_path: str) -> List[Question]:
    with open(file_path, 'r', encoding='utf-8') as file:
        latex_content = file.read()
    
    walker = LatexWalker(latex_content)
    nodes, pos, len_ = walker.get_latex_nodes()
    
    # questions = []
    # current_section = "未分类"
    # current_question_type = "unknown"
    
    for node in nodes:
        # 跳过注释节点
        if isinstance(node, LatexCommentNode):
            continue

        parse_node(node, walker)

def process_tex_files_in_directory(directory: str) -> List[Question]:
    all_questions = []
    for root, dirs, files in os.walk(directory):
        for file in files:
            if file.endswith('.tex'):
                file_path = os.path.join(root, file)
                questions = extract_questions_from_tex_file(file_path)
                all_questions.extend(questions)
    return all_questions

def save_questions_to_database(questions: List[Question], db_connection):
    """
    将解析出的题目保存到数据库
    这里需要根据实际的数据库连接和表结构来实现
    """
    for question in questions:
        # 这里添加数据库保存逻辑
        pass

if __name__ == '__main__':
    # 测试单个文件解析
    tex_file = 'src/main/resources/latex/2017quanguo3.tex'
    extract_questions_from_tex_file(tex_file)
    
    # 打印解析结果
    for question in questions:
        print(f"\nSection: {question.section}")
        print(f"Type: {question.type}")
        print(f"Content: {question.content}")
        if question.type == "choice" and question.options:
            print("Options:")
            for i, option in enumerate(question.options, 1):
                print(f"{i}. {option}")
        print("-" * 50)
    
    # 测试目录处理
    # directory = 'src/main/resources/latex'
    # all_questions = process_tex_files_in_directory(directory)
    # print(f"Total questions found: {len(all_questions)}")
