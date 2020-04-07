package Repository;

import Domain.Tema;
import Validator.Validator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

import static javax.xml.xpath.XPathFactory.newInstance;

public class TemeRepo extends AbstractRepo<Tema,Integer> {
    private String fName;
    private DocumentBuilderFactory builderFactory;
    public TemeRepo(Validator<Tema> val, String n){
        super(val);
        this.fName=n;
        builderFactory=DocumentBuilderFactory.newInstance();
        loadFromFile();
    }
    public void loadFromFile(){
        try{
            DocumentBuilder db=builderFactory.newDocumentBuilder();
            Document d=db.parse(new File(fName));
            Element e = d.getDocumentElement();
            NodeList l=e.getElementsByTagName("Tema");
            for(int i=0;i<l.getLength();i++){
                Element a=(Element) l.item(i);
                String id=a.getAttribute("nr");
                int nr=Integer.parseInt(id);
                NodeList l1=a.getElementsByTagName("descriere");
                String nume=l1.item(0).getTextContent();
                NodeList l2=a.getElementsByTagName("deadline");
                String gr=l2.item(0).getTextContent();
                int dd=Integer.parseInt(gr);
                NodeList l3=a.getElementsByTagName("sapt_primire");
                String mail=l3.item(0).getTextContent();
                int sp=Integer.parseInt(mail);
                Tema t=new Tema(nr,nume,sp,dd);
                super.save(t);
            }
        }
        catch (Exception e){e.printStackTrace();}
    }
    private void writeToFile(){
        try{
            DocumentBuilder db=builderFactory.newDocumentBuilder();
            Document doc=db.newDocument();
            Element r = doc.createElement("Tema");
            for(Tema s:findAll()) {
                Element e = doc.createElement("Tema");
                e.setAttribute("nr",s.getID().toString());
                Element numee=doc.createElement("descriere");
                Text num=doc.createTextNode(s.getDescriere());
                numee.appendChild(num);
                e.appendChild(numee);
                Element grupa=doc.createElement("deadline");
                int g=s.getDeadline();
                String grr=Integer.toString(g);
                Text gr=doc.createTextNode(grr);
                grupa.appendChild(gr);
                e.appendChild(grupa);
                Element mail=doc.createElement("sapt_primire");
                Text email=doc.createTextNode(s.getSaptPrimire().toString());
                mail.appendChild(email);
                e.appendChild(mail);
                r.appendChild(e);
            }
            doc.appendChild(r);
            TransformerFactory tf=TransformerFactory.newInstance();
            Transformer t=tf.newTransformer();
            t.setOutputProperty(OutputKeys.INDENT, "yes");
            t.setOutputProperty(OutputKeys.METHOD, "xml");
            t.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            t.transform(new DOMSource(doc),new StreamResult(new FileOutputStream(fName)));
        }
        catch (Exception e){e.printStackTrace();}
    }
    @Override
    public Tema save(Tema el) {
        Tema t= super.save(el);
        writeToFile();
        return t;
    }
    @Override
    public Tema delete(Integer id){
        Tema t=super.delete(id);
        writeToFile();
        return t;
    }
    @Override
    public Tema update(Tema tt){
        Tema t=super.update(tt);
        writeToFile();
        return t;
    }

}
