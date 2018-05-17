package baas;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@SpringBootApplication
public class Rakendus{
    @Autowired
    private KombDAO kombDao;
    
    @RequestMapping("/loetelu")
    public Iterable<Komb> loetelu(){
        return kombDao.findAll();
		//http://greeny.cs.tlu.ee:20222/loetelu
    }
    
    @RequestMapping("/lisa")
    public String lisa(int n, int m){
        Komb k=new Komb();
        k.n=m;
        k.m=n;
        int resultn = 1;
        int resultm =1;
        int resultmm =1;
        for (int i = 1; i <= m; i++) {
           resultm = resultm * i;}
        for (int j = 1; j <= n; j++) {
           resultn = resultn * j;}
        for (int l = 1; l <= (m-1); l++) {
           resultmm = resultmm * l;}
        float vastus = resultn/(resultm*resultmm);
        k.vastus = vastus;
        kombDao.save(k);
        return "vastuseks on"+vastus;
		//http://greeny.cs.tlu.ee:20222/lisa?mudel=Canyon_Aerod_SL&aasta=2018
    }
    
    
    @RequestMapping("/muuda")
    public String lisa(int id, int n, int m){
        Komb k=kombDao.findOne(id);
        if(k==null){return "Arvutuse nr "+id+" puudub";}
        k.n=n;
        k.m=m;
        int resultn = 1;
        int resultm =1;
        int resultmm =1;
        for (int i = 1; i <= m; i++) {
           resultm = resultm * i;}
        for (int j = 1; j <= n; j++) {
           resultn = resultn * j;}
        for (int l = 1; l <= (m-1); l++) {
           resultmm = resultmm * l;}
        float vastus = resultn/(resultm*resultmm);
        k.vastus = vastus;
        kombDao.save(k);
        return "muudeti arvutus numbriga "+id;
		//http://greeny.cs.tlu.ee:20222/muuda?id=2&mudel=Canyon_Endurace_SL&aasta=2018
    }

    @RequestMapping("/kustuta")
    public String kustuta(int id){
        Komb k=kombDao.findOne(id);
        if(k==null){return "Arvutus nr "+id+" puudub";}
        kombDao.delete(k);
        return "kustutati arvutus nr "+id;
		//http://greeny.cs.tlu.ee:20222/kustuta?id=2
    }
    
   
    
    public static void main(String[] args){
        SpringApplication.run(Rakendus.class, args);
    }
}

//mvn package
//java -jar -Dserver.port=43256 target/baas1-1.jar
//http://greeny.cs.tlu.ee:20222/rataleht.html