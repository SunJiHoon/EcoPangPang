package hackathon.EchoPangPang.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
public class Puang {

    public enum Grade {
        EGG,        //알
        BABY,       //신생아
        CHILD,      //유아
        TEENAGER,   //청소년
        ADULT,       //성인
        GUARDIAN    //수호자
    }

    @Column(name = "puang_name")
    private String name;

    @Column(name = "puang_grade")
    private Grade grade;

    public Puang(String name, Grade grade) {
        this.name = name;
        this.grade = grade;
    }
}
