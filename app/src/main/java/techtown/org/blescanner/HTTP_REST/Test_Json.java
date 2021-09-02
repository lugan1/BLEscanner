package techtown.org.blescanner.HTTP_REST;

import com.google.gson.annotations.SerializedName;

public class Test_Json {
    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @SerializedName("postId")
    public int postId;
    @SerializedName("id1")
    public int id;
    @SerializedName("name")
    public String name;
    @SerializedName("email")
    public String mail;
    @SerializedName("body")
    public String body;

}
