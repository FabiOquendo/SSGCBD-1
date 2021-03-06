package views;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import gestionmodelosconsultas.ModelFactory;
import gestionmodelosconsultas.compilador.CompiladorProyeccion;
import gestionmodelosconsultas.compilador.CompiladorRealizacion;
import gestionmodelosconsultas.modeloconsultas.ModeloConsulta;
import gestionmodelosconsultas.modeloconsultas.ModeloconsultasFactory;
import gestionmodelosconsultas.modeloconsultas.que.Documento;
import gestionmodelosconsultas.modeloconsultas.resultset.ResultElement;
import gestionmodelosconsultas.modeloconsultas.resultset.Resultado;
import gestionmodelosconsultas.modeloconsultas.resultset.resultque.impl.DocumentoImpl;
import gestionmodelosconsultas.world.Padre;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.part.ViewPart;

import whoownme.model.ModelFactoryModel;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Text;



public class RealizacionView extends ViewPart {

	public static final String ID = "views.RealizacionView"; //$NON-NLS-1$
	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());

	ModelFactoryModel modelFactoryModel = ModelFactoryModel.getInstance();
	
	ModelFactory modelFactory;
	
	public RealizacionView() {
		inicializar();
	}
	
	public void inicializar(){
		modelFactory = modelFactoryModel.getModelFactory();
	}

	/**
	 * Create contents of the view part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = toolkit.createComposite(parent, SWT.NONE);
		toolkit.paintBordersFor(container);
		container.setLayout(null);
		
		Button btnCompilar = new Button(container, SWT.NONE);
		btnCompilar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				modelFactory = modelFactory.cargar();
				modelFactoryModel.setModelFactory(modelFactory);
				CompiladorProyeccion compiladorProyeccion = new CompiladorProyeccion();
				compiladorProyeccion.compilarProyeccion(modelFactory);
			}
		});
		btnCompilar.setBounds(10, 10, 103, 25);
		toolkit.adapt(btnCompilar, true, true);
		btnCompilar.setText("Compilar");
		
		Button generarExcel = new Button(container, SWT.NONE);
		generarExcel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				modelFactory = modelFactory.cargar();
				modelFactoryModel.setModelFactory(modelFactory);
				
				
				ModeloConsulta modeloConsulta = modelFactory.getFactoryModeloConsultas().getListModeloConsulta().get(0);
				EList<ResultElement> resultados =  modeloConsulta.getListResultado().get(0).getListResultElement();
				
				try {
					
					 Workbook wb = new XSSFWorkbook();
					 FileOutputStream fileOut;
					fileOut = new FileOutputStream("C:/Users/admin/Desktop/workbook.xlsx");
					
					Sheet sheet = wb.createSheet("Reporte");
					Cell cell = null;
				    
				
				    for (int i = 0; i < resultados.size(); i++) {
				    	
				    	DocumentoImpl doc =  (DocumentoImpl) resultados.get(i);
				    	
				    	Row row = sheet.createRow(i);
					    
				    	cell =  row.createCell(0);
				    	cell = sheet.getRow(i).getCell(0);
					    cell.setCellValue(doc.getTipo());
					    
					    cell =  row.createCell(1);
				    	cell = sheet.getRow(i).getCell(1);
					    cell.setCellValue(doc.getKey());
					    
					    cell =  row.createCell(2);
					    cell = sheet.getRow(i).getCell(2);
					    cell.setCellValue(doc.getFechaCreacion() );
				    }
				    	    
				    
				    wb.write(fileOut);
				    fileOut.close();
				    wb.close();
				    
				    JOptionPane.showMessageDialog(null, "Se gener� el excel");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			    	
			}
		});
		generarExcel.setBounds(130, 10, 130, 25);
		toolkit.adapt(generarExcel, true, true);
		generarExcel.setText("General Excel");
		
		
		createActions();
		initializeToolBar();
		initializeMenu();
	}

	public void dispose() {
		toolkit.dispose();
		super.dispose();
	}

	/**
	 * Create the actions.
	 */
	private void createActions() {
		// Create the actions
	}

	/**
	 * Initialize the toolbar.
	 */
	private void initializeToolBar() {
		IToolBarManager tbm = getViewSite().getActionBars().getToolBarManager();
	}

	/**
	 * Initialize the menu.
	 */
	private void initializeMenu() {
		IMenuManager manager = getViewSite().getActionBars().getMenuManager();
	}

	@Override
	public void setFocus() {
		// Set the focus
	}
}
