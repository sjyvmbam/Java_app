import com.example.lagerapp.Dao.DaoConnection;
import com.example.lagerapp.Dao.DaoConnectionImp;
import com.example.lagerapp.Service.LagerService;
import com.example.lagerapp.Service.LagerServiceImp;
import com.example.lagerapp.model.Medikament;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

public class AddMedikamentTest {

    LagerService lagerService= new LagerServiceImp();
    DaoConnection repo= new DaoConnectionImp();

    @Test
    public void addMedikament(){
        repo.addNewMedikament(new Medikament(331001,90));
        List<Medikament> list=repo.findAll();
        int size=list.size();
        assertEquals(4,size);

    }

    @Test
    public void addMedikamentIfNotExistElseNotAddIfAlreadyExist(){
        Medikament m=new Medikament(1780870,220);
        Medikament medikament=repo.findOne(m.getPharmazentralnummer());
        if(medikament.getPharmazentralnummer()==m.getPharmazentralnummer()){
            assertThat(medikament.getPharmazentralnummer()).isEqualTo(m.getPharmazentralnummer());
        }else {
            repo.addNewMedikament(new Medikament(1780870, 220));
            List<Medikament> list = repo.findAll();
            assertEquals(5, list.size());
        }

    }

    @Test
    public void checkMedikamentExist(){
        Medikament medikament=repo.findOne(111001);
        Medikament m=new Medikament(111001,120);
        assertThat(medikament.getPharmazentralnummer()).isEqualTo(m.getPharmazentralnummer());
    }

    @Test
    public void checkIfstueckGroesserAlsNull(){
        List<Medikament>medikaments=repo.findAll();
        for(int i=0;i< medikaments.size();i++){
            assertThat(medikaments.get(i).getStueckzahl()).isGreaterThan(0);
        }

    }

    @Test
    public void verkaufMedikament(){
        repo.reduktieren(new Medikament(4,100));
        Medikament m=repo.findOne(4);
        assertThat(m.getStueckzahl()).isEqualTo(600);

    }





}