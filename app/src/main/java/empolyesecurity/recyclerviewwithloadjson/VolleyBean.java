package empolyesecurity.recyclerviewwithloadjson;

/**
 * Created by vivid on 1/11/17.
 */

public class VolleyBean {

    public VolleyBean(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    String id;
    String name;
    String imageurl;
    String date;

    public VolleyBean(String name,String imageurl,String date){
        this.name=name;
        this.imageurl=imageurl;
        this.date=date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


}
