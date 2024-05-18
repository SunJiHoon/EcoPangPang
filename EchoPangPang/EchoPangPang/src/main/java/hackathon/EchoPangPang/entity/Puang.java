package hackathon.EchoPangPang.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
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

}
