class Quiz {
    private String name;
    private QuestionAndAnswer[] questions;
    private int noOfQuestions;
    private final int MAX_QUESTIONS;

    public Quiz(String name, int maxQuestions) {
        this.name = name;
        this.MAX_QUESTIONS = maxQuestions;
        this.questions = new QuestionAndAnswer[MAX_QUESTIONS];
        this.noOfQuestions = 0;
    }

    public void addQuestion(String question, String answer) throws MaxNumberOfQuestionsException {
        if (noOfQuestions < MAX_QUESTIONS) {
            questions[noOfQuestions] = new QuestionAndAnswer(question, answer);
            noOfQuestions++;
        } else {
            throw new MaxNumberOfQuestionsException("Dosegnut maksimalni broj pitanja.");
        }
    }

    public int getNoOfQuestions() {
        return noOfQuestions;
    }

    public String getQuestion(int index) {
        if (index >= 0 && index < noOfQuestions) {
            return questions[index].getQuestion();
        } else {
            return "Neispravan indeks pitanja.";
        }
    }

    public boolean isCorrectAnswer(int index, String userAnswer) {
        if (index >= 0 && index < noOfQuestions) {
            return questions[index].isCorrectAnswer(userAnswer);
        } else {
            return false;
        }
    }

    private static class QuestionAndAnswer {
        private String question;
        private String answer;

        public QuestionAndAnswer(String question, String answer) {
            this.question = question;
            this.answer = answer;
        }

        public String getQuestion() {
            return question;
        }

        public boolean isCorrectAnswer(String userAnswer) {
            return answer.equalsIgnoreCase(userAnswer);
        }
    }
}

class MaxNumberOfQuestionsException extends Exception {
    public MaxNumberOfQuestionsException(String message) {
        super(message);
    }
}