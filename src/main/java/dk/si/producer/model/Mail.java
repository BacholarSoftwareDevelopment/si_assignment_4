package dk.si.producer.model;

public class Mail {

    private Member member;
    private Gender gender;
    private String content, salutation;

    public Mail(Member member, String content) {
        this.member = member;
        //this.gender = gender;
        this.content = content;
        //setSalutation();
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSalutation() {
        return salutation;
    }

    public void setSalutation() {
        if (getGender().toString().equals(Gender.MALE.toString())) {
            this.salutation = "Mr.";
        } else {
            this.salutation = "Mrs.";
        }
    }
}