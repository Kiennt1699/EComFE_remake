package Domain;

public class ChatMessage {
    private String content;
    private boolean isAnswer;

    public ChatMessage(String content, boolean isAnswer) {
        this.content = content;
        this.isAnswer = isAnswer;
    }

    public ChatMessage() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isAnswer() {
        return isAnswer;
    }

    public void setAnswer(boolean answer) {
        isAnswer = answer;
    }
}
