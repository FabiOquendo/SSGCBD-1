package reportes.view;

import tooldataform.ModelFactory;
import tooldataform.core.CoreFactory;
import tooldataform.core.Domain_Diagram;
import tooldataform.core.Project;
import tooldataform.formmodel.concreta.ColumView;
import tooldataform.formmodel.concreta.ConcretaFactory;
import tooldataform.formmodel.concreta.Container;
import tooldataform.formmodel.concreta.DataForm_Diagram;
import tooldataform.formmodel.concreta.Interface;
import tooldataform.formmodel.concreta.LabelView;
import tooldataform.formmodel.concreta.TableView;
import tooldataform.pmoo.Clase;
import tooldataform.pmoo.PmooFactory;
import tooldataform.pmoo.Valores;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class GeneracionDF {

	 ModelFactory mf;
	 Project project;
	 DataForm_Diagram dfDiagram;
	 Domain_Diagram dmDiagram;
	 
	 Clase domainClase;
	 Interface interface1;
	 String ruta;
	 String absolutePath;
	 ArrayList<String> listaTablas;
	  
	 int nTablas;
	 int nx;
	 int ny;
	 int xs[] = {1,-1, 0, 0};
	 int ys[] = {0,0, 1, -1};
	
	 public GeneracionDF(String ruta,String as){
		 this.ruta = ruta+"/xl/";
		 this.absolutePath = as;
	 }
	
	public void generate() throws ParserConfigurationException, SAXException, IOException, EncryptedDocumentException, InvalidFormatException{
		Init();
		getTables();
		MarcarTablas(listaTablas);
		getExtraInformation();
		salvar();
	}
	public void Init() throws ParserConfigurationException, SAXException, IOException{
		
		String dimens = getDimention();
		String inicio = dimens.split(":")[0];
		String fin = 	dimens.split(":")[1];
		Nodo coordinate = getCoordinates(inicio);
		
		nx = coordinate.x;
		ny = coordinate.y;
		
		Nodo size = getSizes(inicio, fin);
		
		System.out.println(dimens);
		mf= tooldataform.TooldataformFactory.eINSTANCE.createModelFactory();
		project = CoreFactory.eINSTANCE.createProject();
		dfDiagram = ConcretaFactory.eINSTANCE.createDataForm_Diagram();
		dmDiagram = CoreFactory.eINSTANCE.createDomain_Diagram();
		interface1 = ConcretaFactory.eINSTANCE.createInterface();
		interface1.setWidth(new Integer(size.x  + 100));
		interface1.setHeight(new Integer(size.y + 200));
		System.out.println( (size.x + 100) + " " + (size.y+200));
		dfDiagram.setTheInterface(interface1);
		dfDiagram.setOwnedByProject(project);
		
		domainClase = PmooFactory.eINSTANCE.createClase();
		domainClase.setName("Domain");
		dmDiagram.getListClase().add(domainClase);
		project.getListDiagram().add(dmDiagram);
		project.getListDataFormDiagram().add(dfDiagram);
		project.setTheModelFactory(mf);
		mf.getListProyecto().add(project);
		listaTablas = new ArrayList<String>();
	}
	
	public void getExtraInformation() throws EncryptedDocumentException, InvalidFormatException, IOException{
		InputStream is = new FileInputStream(absolutePath);
        Workbook libro = WorkbookFactory.create(is);
        is = new FileInputStream("C:/Users/admin/Desktop/VisitadosLibro1.xlsx");
        Workbook visit = WorkbookFactory.create(is);       
        recorrerHoja(libro, visit);
	}
	

	public String getDimention()throws ParserConfigurationException, SAXException, IOException{
		Document doc = getXML(ruta+"worksheets/sheet1.xml");
		doc.getDocumentElement().normalize();	
		NodeList nList = doc.getElementsByTagName("dimension");
		Node node = nList.item(0);
		String s="";
		if(node.getNodeType()==Node.ELEMENT_NODE){
			Element e = (Element)node;
			s = e.getAttribute("ref");
		}
		return s;
	}
	public  int getNumberTables() throws ParserConfigurationException, SAXException, IOException{	
		Document doc = getXML(ruta+"worksheets/sheet1.xml");
		doc.getDocumentElement().normalize();	
		NodeList nList = doc.getElementsByTagName("tablePart");
		return nList.getLength();
	}
	
	public void getTables() throws ParserConfigurationException, SAXException, IOException{
		int n = getNumberTables();
		nTablas = n;
		Document doc;
		for(int i=1;i<=n;i++){
			Container containerTablas = ConcretaFactory.eINSTANCE.createContainer();
			containerTablas.setName("Table"+i);
			doc = getXML(ruta +"tables/table"+i+".xml");
			
			String s="";
			NodeList es = doc.getElementsByTagName("autoFilter");
			Node node = es.item(0);
			if(node.getNodeType()==Node.ELEMENT_NODE){
				Element e = (Element)node;
				s = e.getAttribute("ref");
				listaTablas.add(s);
			}
			
			String inicio = s.split(":")[0];
			String fin = 	s.split(":")[1];
			Nodo coordinates= getCoordinates(inicio);
			Nodo size = getSizes(inicio, fin);
	
			NodeList nList = doc.getElementsByTagName("tableColumn");
			ArrayList<String> columnas = new ArrayList<String>();
			
			for (int j = 0; j < nList.getLength(); j++) {
				Node nNode = nList.item(j);
				if(nNode.getNodeType()==Node.ELEMENT_NODE){
					Element e = (Element)nNode;
					columnas.add(e.getAttribute("name"));
				}
			}
			TableView tb = ConcretaFactory.eINSTANCE.createTableView();
			tb.setName("Table"+i);
			for(int j=0;j<columnas.size();j++){
				ColumView c= ConcretaFactory.eINSTANCE.createColumView();
				c.setName(columnas.get(j));
				tb.getListColumView().add(c);
			}
			tb.setWidth(new Integer(size.x));
			tb.setHeight(new Integer(size.y));
			tb.setPositionX(new Integer(15));
			tb.setPositionY(new Integer(15));
			
			containerTablas.setWidth(new Integer(size.x + 40));
			containerTablas.setHeight(new Integer(size.y+ 40));
			containerTablas.setPositionX(new Integer(coordinates.x - nx + 40 ));
			containerTablas.setPositionY(new Integer(coordinates.y - ny + 40));
			containerTablas.getListGraphicalContainer().add(tb);
			tooldataform.formmodel.concreta.Containment c= ConcretaFactory.eINSTANCE.createContainment();
			c.setAMultiplicidad(tooldataform.pmoo.Cardinalidad.N);
			c.setBMultiplicidad(tooldataform.pmoo.Cardinalidad.N);
			c.setANavegable(Valores.SI);
			c.setBNavegable(Valores.SI);
			c.setBinding(Valores.SI);
			c.setARol("ownedByTable"+i);
			c.setBRol("listTable"+i);
			c.setName("name"+i);
			c.getSource().add(containerTablas);
			c.getTarget().add(tb);
			dfDiagram.getListarelacion().add(c);
			dfDiagram.setTheInterface(interface1);
			interface1.getListGraphicalContainer().add(containerTablas);
		}	
	}
	
	public void salvar(){
		org.eclipse.emf.common.util.URI uri = org.eclipse.emf.common.util.URI.createURI("platform:/resource/GestionConsultas/modelos/model.tooldataform");
		org.eclipse.emf.ecore.resource.ResourceSet resourceSet= new org.eclipse.emf.ecore.resource.impl.ResourceSetImpl();
		org.eclipse.emf.ecore.resource.Resource resource = resourceSet.createResource(uri);
		resource.getContents().add(mf);
		try {
			resource.save(java.util.Collections.EMPTY_MAP);
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}

	}
	
	public Document getXML(String url)throws ParserConfigurationException, SAXException, IOException{
		File fXmlFile = new File(url);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
		return doc;
	}
	
	public void MarcarTablas(ArrayList<String> sizes) throws IOException, EncryptedDocumentException, InvalidFormatException{
        Workbook visit = new XSSFWorkbook();
        Sheet sheetV =  visit.createSheet();
        for(int i=0;i<sizes.size();i++){
        	String inicio = sizes.get(i).split(":")[0], fin =  sizes.get(i).split(":")[1];
        	for(int  j = Integer.parseInt(capturarNumeros(inicio))-1 ; j <Integer.parseInt(capturarNumeros(fin)) ;j++){
        		Row r= sheetV.getRow(j);
        		if(r==null)
        			r= sheetV.createRow(j);
        		
        		int wf= toInt(stractColumn(fin))-1;
        		int wi = toInt(stractColumn(inicio))-1;
        		
        		for(int k = wi; k <=wf;k++ ){
        			Cell c = r.createCell(k);
        			c.setCellValue(1);
        		}
        	}
        }
        FileOutputStream fos = new FileOutputStream("C:/Users/admin/Desktop/VisitadosLibro1.xlsx");
	    visit.write(fos);
	    visit.close();
	    fos.close();
	}
	
	public static boolean isValid(int i, int j) {
		if(i >= 0 && i < 10  && j >= 0 && j < 10)
			return true;
		return false;
	}
	
	
	public ArrayList<Nodo> bfs(int i, int j,Sheet sheetL,Sheet sheetV) {	
		
		Row r = sheetL.getRow(i);
		Cell c = r.getCell(j);
		
		ArrayList<Nodo> res = new ArrayList<Nodo>();
		
		
 		Row rv = sheetV.getRow(i);
		Cell cv = r.createCell(j);
		cv.setCellValue(1);
		
		
		Queue<Cell> q =   new LinkedList<Cell>();
		q.add(c);
		
		while (!q.isEmpty()) {
			
			Cell u = q.peek();
			q.poll();
			for(int k = 0; k < 4; k++) {
				int vx = u.getRowIndex()    + xs[k];
				int vy = u.getColumnIndex() + ys[k];
				
				if(isValid(vy, vy)){
					r = sheetL.getRow(vx);
					if(r==null) continue;
					
					c = r.getCell(vy);
					if (c !=null  ) {
						rv = sheetV.getRow(vx);
						if(rv==null){
							rv=sheetV.createRow(vx);
							cv=rv.createCell(vy);
							cv.setCellValue(1);
							res.add(new Nodo(vx, vy));
							q.add(c);
						}else{
							cv = rv.getCell(vy);
							if(cv == null) {
								cv = rv.createCell(vy);
								cv.setCellValue(1);
								res.add(new Nodo(vx, vy));
								q.add(c);
							}
						}
					}						
				}
			}
		}
		return res;
	}

	public void recorrerHoja(Workbook libro, Workbook visit) throws IOException {
		Sheet sheetL =  libro.getSheetAt(0);
		Sheet sheetV =  visit.getSheetAt(0);
		int nmax = sheetL.getLastRowNum()+1;
		
		for (int i = 0; i <nmax; i++) {
        	Row r =  sheetL.getRow(i);
        	if(r==null) continue;
        	
        	Row rv = sheetV.getRow(i);
        	if(rv ==null)
        		rv = sheetV.createRow(i);
        	
        	for (int j = 0; j <r.getLastCellNum(); j++) {
	    		Cell c = r.getCell(j);
	    		Cell cv = rv.getCell(j);
	    	
	            if(c != null && cv==null ) { 
	            	if(c.getCellType()==Cell.CELL_TYPE_BLANK)
	            		continue;
	            	ArrayList<Nodo> res = bfs( i, j,sheetL,sheetV);
	            	Collections.sort(res);
	            	int m = res.size();
	            	
	            	for(int k=0;k<m;k++){
	            		System.out.println(res.get(k).x + " " + res.get(k).y);
         			}
	            	System.out.println();
	            	
	            	if(m>1){
	            		String key =res.get(0).x +"-"+ res.get(0).y+":" + res.get(m-1).x +"-" + res.get(m-1).y ;
	            		
	            	
	            		String inicio = key.split(":")[0];
	                    String fin  = key.split(":")[1];
	                    
	                    
	                    Container containerTablas = ConcretaFactory.eINSTANCE.createContainer();
	        			containerTablas.setName("Container"+ (nTablas++));
	        		
	        			System.out.println(inicio+ " " +  fin);
	         			Nodo coordinates=getCoordinates2(inicio);
	         			Nodo size = getSizes2(inicio, fin);
	         			containerTablas.setWidth(new Integer(size.x + 40));
	        			containerTablas.setHeight(new Integer(size.y + 40));
	        			containerTablas.setPositionX(new Integer(coordinates.x - nx + 40));
	        			containerTablas.setPositionY(new Integer(coordinates.y -ny + 40));
	         			
	         			Nodo relativo = getCoordinates2(res.get(0).x +"-" +res.get(0).y);
	         			int relativex = relativo.x ;
	         			int relativey = relativo.y ;
	         			
	         			for(int k=0;k<m;k++){
	         				int x = res.get(k).x;
	         				int y = res.get(k).y;
	         				Row rr = sheetL.getRow(x);
	         				Cell  cc = rr.getCell(y);
	         				Nodo cor = getCoordinates2(x+"-"+y);
	         				LabelView label = ConcretaFactory.eINSTANCE.createLabelView();
	         				label.setName("Hola" + (char)(65+k));
	         				label.setWidth(new Integer(-1));
		        			label.setHeight(new Integer(-1));
		        			label.setPositionX(new Integer(cor.x  - relativex + 15 ));
		        			label.setPositionY(new Integer(cor.y  - relativey + 15));
	         				containerTablas.getListIndividualElementDataForm().add(label);
	         			}
	         			interface1.getListGraphicalContainer().add(containerTablas);
	            		System.out.println(key + " " + "Container"+(nTablas));
	            	}
	            }
	        }
	    }
		FileOutputStream fos = new FileOutputStream("C:/Users/admin/Desktop/VisitadosLibro1.xlsx");
	    visit.write(fos);
	    visit.close();
	    fos.close();
	}
	
	public Nodo getCoordinates(String inicio){
		Nodo res;
		
		int wi = toInt(stractColumn(inicio))-1;
		int w = (wi) *120;
		int h = ( Integer.parseInt(capturarNumeros(inicio))-1)*25;
		res = new Nodo(w, h);
		return res;
	}
	
	public String capturarNumeros(String s){
		int i=0;
		for(;i<s.length();i++){
			if( s.charAt(i) >= 65 && s.charAt(i)<=90){
				continue;
			}else{
				break;
			}
		}
		return s.substring(i);
	}
	
	public Nodo getSizes(String inicio,String fin){
		Nodo res;
		int wf= toInt(stractColumn(fin))-1;
		int wi = toInt(stractColumn(inicio))-1;
		
		int w = ( wf - wi + 1)*100;
		int h = ( Integer.parseInt(capturarNumeros(fin)) - Integer.parseInt(capturarNumeros(inicio)) + 1)*23;  
		res = new Nodo(w, h);
		return res;
	}
	
	public String stractColumn(String s){
		
		String res="";
		for(int i=0;i<s.length();i++){
			if( s.charAt(i) >= 65 && s.charAt(i)<=90){
				res+= s.charAt(i);
			}else{
				break;
			}
		}
		
		
		return res;
	}
	
	public  Nodo getSizes2(String inicio,String fin){
		Nodo res;
		
		String ws1 = inicio.split("-")[1];
		String hs1 = inicio.split("-")[0];
		
		String ws2 = fin.split("-")[1];
		String hs2 = fin.split("-")[0];
		
		int w = ( (Integer.parseInt(ws2))  -  (Integer.parseInt(ws1)) + 1 )*100;
		int h = ( Integer.parseInt(hs2) - Integer.parseInt((hs1)) + 1)*23 ;  
		res = new Nodo(w, h);
		return res;
	}
	
	public  Nodo getCoordinates2(String inicio){
		Nodo res;
		String ws = inicio.split("-")[1];
		String hs = inicio.split("-")[0];
		int w = Integer.parseInt(ws) *120;
		int h = Integer.parseInt(hs) *25;
		res = new Nodo(w, h);
		return res;
	}
	
	
	public static int toInt(String s){
		int res =0;
		int m = s.length()-1;
		int e=0;
		for(int i=m;i>=0;i--){
			res += ( (s.charAt(i) - 64))*pow(26,e);
			e++;
		}
		return res;
	}
	
	public static  int pow(int b,int e){
		int res =1;
		
		for(int i=0;i<e;i++){
			res*=b;
		}
		return res;
	}
}