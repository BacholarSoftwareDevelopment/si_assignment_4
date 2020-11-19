package dk.si.producer.model;

import dk.si.producer.service.Service;

import java.io.IOException;

public class Mail {

    private Member member;
    private Gender gender;
    private String content, salutation;

    public Mail(Member member, String content) throws IOException {
        this.member = member;
        this.content = content;
        setGender();
        setSalutation();
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
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

    public Gender getGender() {
        return gender;
    }

    public void setGender() throws IOException {
        String isMale = new Service().getXMLDataGenderByName(member.getName())
                .split("<male>")[1]
                .split("</male>")[0]
                .trim();
        if(isMale.equals("true")){
            this.gender = Gender.MALE;
        } else {
            this.gender = Gender.FEMALE;
        }
    }

    public void setSalutation() {
        if (getGender().toString().equals(Gender.MALE.toString())) {
            this.salutation = "Mr.";
        } else {
            this.salutation = "Mrs.";
        }
    }
}