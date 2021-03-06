package com.gradtest.ETC;

/**
 * Created by SM-PC on 2018-04-13.
 */

public class ItemForm {

    private String id;//보안을 위해서 private로
    private int imageNumber;
    private String txt;
    private int board_index;
    private String timeTxt;

    public String getTimeTxt() {
        return timeTxt;
    }

    public void setTimeTxt(String timeTxt) {
        this.timeTxt = timeTxt;
    }

    public int getBoard_index() {
        return board_index;
    }

    public void setBoard_index(int board_index) {
        this.board_index = board_index;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    private String content;

    public ItemForm(String id1, int imageNumber1, String txt, String content, String timeTxt){//new생성자를 통해서 생성자가 만들어진다.
        this.id = id1;
       // this.imageNumber = imageNumber1;
        this.txt = txt;
        this.content = content;
        this.timeTxt = timeTxt;

    }
    public String getId(){//외부로 id값을 리턴해서 내보내준다.

        return id;
    }


    /*
    public int getImageNumber(){//외부로 이미지 값을 리턴해서 보내준다.

        return imageNumber;
    }*/

    public String getTxt(){

        return txt;
    }
    public void setId(String id1){//외부에서 받은 id를 내부로 넣어준다.

        this.id=id1;
    }

    /*
    public void setImageNumber(int imageNumber1){//외부에서 받은 imagenumber를 내부로 넣어준다.

        this.imageNumber = imageNumber1;
    }*/

    public void setTxt(String txt){
        this.txt = txt;

    }


    public int getImageNumber() {
        return imageNumber;
    }

    public void setImageNumber(int imageNumber) {
        this.imageNumber = imageNumber;
    }
}
