import os
from pylatexenc.latexwalker import LatexWalker, LatexEnvironmentNode

def extract_questions_from_tex_file(file_path):
    with open(file_path, 'r', encoding='utf-8') as file:
        latex_content = file.read()
    walker = LatexWalker(latex_content)
    nodes, pos, len_ = walker.get_latex_nodes()
    questions = []
    for node in nodes:
        if isinstance(node, LatexEnvironmentNode) and node.environmentname == 'question':
            question_text = walker.get_latex_text(pos=node.nodelist.pos, len=node.nodelist.len)
            questions.append(question_text)
    return questions

def process_tex_files_in_directory(directory):
    all_questions = []
    for root, dirs, files in os.walk(directory):
        for file in files:
            if file.endswith('.tex'):
                file_path = os.path.join(root, file)
                questions = extract_questions_from_tex_file(file_path)
                all_questions.extend(questions)
    return all_questions

# 假设题目在名为question的环境中，你可以根据实际情况修改
resource_directory = 'resources/tex'
questions = process_tex_files_in_directory(resource_directory)
for i, question in enumerate(questions, start=1):
    print(f"Question {i}: {question}")
