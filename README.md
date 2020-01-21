# Quiz 
Quiz has 3 java files and * .txt files with questions and answers. 
Each *.txt file represents different category of questions. 
Files * .txt are built in way, where:
        -first line is a question,
        -next line is a number of answers, this line gives information how many lines will be reserved for answers 
        -next lines are answers 
        -and so on 
Class QuestionImport is responsible for importing of files content to lists. 
Each question is added to the lists as a new obeject with few attributes.
Class Main has few methods which give possibility to choose category of questions, print out of questions and answers in "random" sequence and checking if answers is correct. User has the time limit of 30 second for the answer. Interaction is made by console.
