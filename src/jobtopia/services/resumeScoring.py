import PyPDF2
import docx2txt
import nltk
from pdfminer.high_level import extract_text
from nltk.tokenize import word_tokenize
from nltk.corpus import stopwords
import spacy
import pymysql


# def get_database_skills():
#     conn = pymysql.connect(
#         host='127.0.0.1',
#         user='root',
#         password='root',
#         db='jobtopia'
#     )
#     with conn.cursor() as cursor:
#         cursor.execute('SELECT nom FROM skills ')
#         results = cursor.fetchall()
#         skills = [r[0] for r in results]
#     conn.close()
#     return skills
def get_database_skills(nom):
    conn = pymysql.connect(
        host='127.0.0.1',
        user='root',
        password='root',
        db='jobtopia'
    )
    with conn.cursor() as cursor:
        cursor.execute(f"SELECT skill1, skill2, skill3 FROM offres WHERE titre='{nom}'")
        results = cursor.fetchall()
        skills = [val.lower() for row in results for val in row] # concatenate values and convert to lowercase
    conn.close()
    return skills
# convert PDF or Word CV to plain text


def extract_text2(filename):
    if filename.endswith('.pdf'):
        pdf_file = open(filename, 'rb')
        reader = PyPDF2.PdfFileReader(pdf_file)
        text = ''
        for i in range(reader.getNumPages()):
            pdf_page = reader.getPage(i)

         # Extract text from the page
            page_text = pdf_page.extractText()

            # Append the page text to the overall text
            text += page_text
        pdf_file.close()
    elif filename.endswith('.docx'):
        text = docx2txt.process(filename)
    else:
        raise ValueError('Unsupported file format')
    return extract_text(filename)


def extract_text_from_pdf(pdf_path):
    return extract_text(pdf_path)

# preprocess text
def preprocess(text):
    stop_words = set(stopwords.words('english'))
    tokens = word_tokenize(text.lower())
    filtered_tokens = [
        word for word in tokens if word.isalpha() and word not in stop_words]
    return ' '.join(filtered_tokens)


# extract skills using Named Entity Recognition
def extract_skills(input_text, COLUMNS_DB):
    stop_words = set(nltk.corpus.stopwords.words('english'))
    word_tokens = nltk.tokenize.word_tokenize(input_text)

    # remove the stop words
    filtered_tokens = [w for w in word_tokens if w not in stop_words]

    # remove the punctuation
    filtered_tokens = [w for w in word_tokens if w.isalpha()]

    # generate bigrams and trigrams (such as artificial intelligence)
    bigrams_trigrams = list(
        map(' '.join, nltk.everygrams(filtered_tokens, 2, 3)))

    # we create a set to keep the results in.
    found_skills = set()

    # we search for each token in our skills database
    for token in filtered_tokens:
        if token.lower() in COLUMNS_DB:
            found_skills.add(token)

    # we search for each bigram and trigram in our skills database
    for ngram in bigrams_trigrams:
        if ngram.lower() in COLUMNS_DB:
            found_skills.add(ngram)

    return found_skills


# connect to MySQL database and query for skills


def jaccard_similarity(list1, list2):
    list1 = [x.lower() for x in list1]
    list2 = [x.lower() for x in list2]

    intersection = len(list(set(list1).intersection(list2)))
    union = (len(list1) + len(list2)) - intersection
    return (float(intersection) / union) * 100

# compare extracted skills with database skills


def compare_skills(database_skills,extracted_skills):
    scores = []
    for skill in database_skills:
        score = jaccard_similarity(extracted_skills, skill.split())
        scores.append(score)
    return scores


# calculate score based on matched skills
def calculate_score(matched_skills, total_skills):
    return sum(matched_skills) / total_skills


# main function
def extract_and_score(nom,filename):
    database_skills = get_database_skills(nom)
    text = extract_text2(filename)
    preprocessed_text = preprocess(text)
    extracted_skills = extract_skills(text, database_skills)
    scores = compare_skills(database_skills,extracted_skills)
    resultat = jaccard_similarity(extracted_skills,database_skills)
    total_skills = len(database_skills)
    score = calculate_score(scores, total_skills)
    return database_skills, resultat, extracted_skills

nom=input("")
filename=input("")
score, resltat, extracted_skills = extract_and_score(nom,filename)
# print(extracted_skills)
# print("{:.2f}".format(resltat))
print(resltat)