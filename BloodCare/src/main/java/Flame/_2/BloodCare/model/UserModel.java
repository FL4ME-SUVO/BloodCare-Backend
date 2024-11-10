package Flame._2.BloodCare.model;

public class UserModel {

    private final long id;
    private final String content;

    public UserModel(long id ,String content){
        this.id = id;
        this.content = content;
    }

    public long getId(){
        return id;
    }

    public String getContent(){
        return content;
    }

}
