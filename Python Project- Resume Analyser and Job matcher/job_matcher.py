import nltk 
from nltk.tokenize import word_tokenize
from nltk.corpus import stopwords

skills = [ 'java', 'c++', 'python', 'sql', 'html', 'css',
         'machine learning', 'cloud', 'data analytics', 'javascript',
          'git', 'excel', 'data structures', 'project management', 
          'problem solving', 'communication', 'networking', 'leadership', 
          'teamwork', 'agentic ai']

resources={
    'python'            : 'GeeksForGeeks',
    'java'              : 'W3Schools or CodewithHary',
    'sal'               : 'SQLZoo',
    'c++'               : 'Apna College',
    'html'              : 'W3Schools HTML',
    'css'               : 'W3Schools CSS Tutorial',
    'machine learning'  : 'Coursera ML',
    'cloud'             : 'Gate Smashers',
    'data analytics'    : 'Google Learning',
    'javascript'        : 'GeeksForGeeks',
    'git'               : 'GitHub Learning Lab',
    'excel'             : 'Microsoft Tutorials',
    'data structures'   : 'CodeBashers',
    'project management': 'Coursera',
    'problem solving'   : 'LeetCode',
    'communication'     : 'Tutorials and Practice',
    'networking'        : 'Cisco Free Courses',
    'leadership'        : 'LinkedIn Learning',
    'teamwork'          : 'Real life practices',
    'agentic ai'        : 'upGrad Course'
}

def divider():
    print(" "+ "="*40)

def system_message(message):
    print(" [Smart Resume Analyzer] " + message)

def intro():
    print("")
    divider()
    print("        Smart Resume Analyzer  ")
    print("      Resume Analyzer Assistant  ")
    divider()
    print("")
    system_message(" Hello! I am Resume Analyzer assistant ")
    system_message(" Your personal Smart Resume Analyzer ")
    system_message(" I can analyze your resume and give you an overview of required skills... ")
    divider()
    print("")

def text_filter(text):
    words = word_tokenize(text.lower())
    stop_words = set(stopwords.words('english'))
    filtered = []
    for word in words:
        if word not in stop_words and word.isalpha():
            filtered.append(word)
    return filtered

def find_skills(filtered_words):
    found = []
    for word in filtered_words:
        if word in skills:
            if word not in found:
                found.append(word)
    index=0
    while index < len(filtered_words)-1:
        two_words = filtered_words[index] + " " + filtered_words[index+1]
        if two_words in skills:
            if two_words not in found:
                found.append(two_words)
        index+=1
    return found

def skill_match(resume_skill, job_skill):
    matched = []
    missing = []
    extra = []
    for skill in job_skill:
        if skill in resume_skill:
            matched.append(skill)
        else:
            missing.append(skill)
    for skill in resume_skill:
        if skill not in job_skill:
            extra.append(skill)
    return matched, missing, extra 

def cal_score(matched, job_skill):
    if len(job_skill) == 0:
        return "No skill found"
    result = (len(matched)/len(job_skill))*100
    result = round(result,1)

    if result >=85:
        remark = "Excellent Match!!"
    elif result >=60:
        remark = "Good Match!"
    elif result >=45:
        remark = "Average match"
    elif result >=20:
        remark = "Weak Match. Resume need to be improved"
    else:
        remark = "Very Weak Match. Major skills are missing"
    return result, remark

def print_re(resume_skill, job_skill, matched, missing, extra, result, remark,username):
    print("")
    divider()
    print(" RESUME RESULTS FOR: " + username.upper())
    divider()

    print("")
    system_message("Skills found in your resume:")
    print(" --> Resume Skills(" + str(len(resume_skill)) + " found)")
    for skill in resume_skill:
        print("   ->" + skill)

    print("")
    system_message(" Skills Required for this job :")
    print(" --> Job required skills (" + str(len(job_skill)) + " found)")
    for skill in job_skill:
        print("   ."+skill)
    
    print("")
    system_message(" Skills Matched:")
    print(" --> Matched Skills ( " + str(len(matched)) + " skills)")
    if len(matched) == 0:
        print("  No skills matched. ")
    for skill in matched:
        print("  >>: " +skill)

    print("")
    system_message(" Skills Missing from your resume: ")
    print(" --> Missing Skills(" + str(len(missing)) + " skills)")
    if len(missing)==0:
        print(" No skills missing")
    for skill in missing:
        print("   >>:" + skill)

    print("")
    system_message(" Extra skills you have :")
    print(" --> Extra Skills (" +str(len(extra)) + " skills)")
    if len(extra) == 0:
        print(" No extra skills found. ")
    for skill in extra:
        print("   >> :" + skill)

    print("")
    divider()
    print("     Score Card   ")
    divider()
    print(" Score: " +str(result) + "%")
    print(" Remark :" + remark)
    divider()

def advice(result, matched, missing, resume_skill, username):
    print("")
    divider()
    print(" ADVICE FOR :" + username.upper())
    divider()
    print("")

    if result>=85:
        system_message(" Amazing!" + username + "Your resume is an excellent match")
        system_message(" You can Apply for this job immediately!")
        print("")
        system_message(" Focus on these skills in interview: ")
        for skill in matched:
            print("  ->"+skill)

    elif result >=60:
        system_message("Good " + username + " You are good for this role")
        system_message(str(len(missing))+" more skills will make you stronger")
        print("")
        system_message(" Focus on learning these skills: ")
        count=1
        for skill in missing:
            print("  ->"+skill)
            count = count+1

    elif result >=45:
        system_message("Not Bad" + username + "but more improvement needed")
        system_message("Missing" + str(len(missing))+ "skills")
        print("")
        system_message(" Please go through these: ")
        count=1
        for skill in missing:
            print("  ->"+skill)
            count =count+1

    else:
        system_message(" After analyzing your resume," + username+"....")
        system_message(" Your resume is missing more skills and need improvement.")
        system_message(" But Do not worry - I have a plan!")
        print("")
        system_message(" Start with these skills first:")
        count =1
        for skill in missing:
            print("    " + str(count)+ ". "+ skill)
            count=count+1
            if count > 3:
                system_message("Learn these 3 first then come back!")
                break



def road_map(missing,username):
    if len(missing)==0:
        system_message(" No roadmap needed. You have all required skills.")
        return 
    print("")
    divider()
    print(" learning roadmap for :"+username.upper())
    divider()
    print("")
    system_message("Here is proper learning plan:")
    print("")
    
    count=1
    for skill in missing:
        print(" STEP "+str(count)+" :")
        print(" Skill :"+ skill.upper())
        if skill in resources:
            print(" Resource :"+resources[skill])
        else:
            print("  Resourse: Search on YouTube")
        print("")
        count =count+1
    divider()
    system_message("Complete 1 skill at a time!")
    system_message("Add each skill ti resume after learning")
    divider()


def Dashboard():
    print("")
    divider()
    print(" What would you like to do next?")
    divider()
    print(" 1. Analyze resume again")
    print(" 2.View learning Roadmap Again")
    print(" 3.Exit")
    divider()
    choice=input( "Enter your choice (1/2/3):")
    return choice



intro()
username = input(" [Smart Resume assistant] May I know your name?:")
print("")
system_message("Welcome "+username)
system_message("I will analyze your resume")

running = True

while running:
    print("")
    system_message("Loading Resume and Job Description")
    
    try:
        resume_input = open("Resume.txt", "r").read()
        job_input = open("Jobdesc.txt", "r").read()
        system_message("Resume loaded successfully")
        system_message("JD loaded successfully")
    except FileNotFoundError:
        system_message("ERROR : Resume.txt or Jobdesc.txt not found!")
        system_message("Both files should be in same folder.")
        break

    system_message("Analyzing your resume now...")

    resume_words = text_filter(resume_input)
    job_words = text_filter(job_input)

    resume_skill = find_skills(resume_words)
    job_skill = find_skills(job_words)

    matched, missing, extra = skill_match(resume_skill, job_skill)

    result, remark = cal_score(matched, job_skill)

    print_re(resume_skill, job_skill, matched, missing, extra, result, remark,username)

    advice(result,matched,missing,resume_skill,username)

    road_map(missing,username)

    choice = Dashboard()

    if choice=="1":
        system_message("Update Resume.txt and jobdesc.txt then press enter")
        input(" Press Enter when ready:")
    elif choice == "2":
        road_map(missing,username)
        input(" press enter to continue ")
    elif choice == "3":
        print("")
        divider()
        system_message("Thank you for using resume analyzer assistant" + username)
        system_message("Keep Learning!")
        divider()
        print("")
        running=False
    else :
        system_message("Invalid choice")
