package reportes.view;

import gestionmodelosconsultas.compilador.CompiladorProyeccion;
import gestionmodelosconsultas.entitymodel.Entity;
import gestionmodelosconsultas.entitymodel.EntitymodelFactory;
import gestionmodelosconsultas.memoms.comunicacion.Comunicacion;
import gestionmodelosconsultas.memoms.comunicacion.Memorando;
import gestionmodelosconsultas.modeloconsultas.FactoryModeloConsulta;
import gestionmodelosconsultas.modeloconsultas.ModeloConsulta;
import gestionmodelosconsultas.modeloconsultas.como.ComoFactory;
import gestionmodelosconsultas.modeloconsultas.impl.FactoryModeloConsultaImpl;
import gestionmodelosconsultas.modeloconsultas.model.EADiagram;
import gestionmodelosconsultas.modeloconsultas.model.ElementoConsulta;
import gestionmodelosconsultas.modeloconsultas.model.Relacion;
import gestionmodelosconsultas.modeloconsultas.model.ViewModel;
import gestionmodelosconsultas.modeloconsultas.model.impl.EADiagramImpl;
import gestionmodelosconsultas.modeloconsultas.que.Documento;
import gestionmodelosconsultas.modeloconsultas.que.QueFactory;
import gestionmodelosconsultas.modeloconsultas.resultset.Resultado;
import gestionmodelosconsultas.modeloconsultas.resultset.resultque.impl.DocumentoImpl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import javax.swing.UIManager;

import model.FactoryModel;
import model.uimanager.ContenedorMemorando;
import model.uimanager.MemorandoViewModel;
import model.uimanager.UimanagerFactory;
import model.uimanager.impl.ContenedorMemorandoImpl;
import model.uimanager.impl.MemorandoViewModelImpl;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;

import pruebamemorandos.view.view.views.VentanaMemorandoViewPart;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import reportes.model.ModelFactoryModel;
import tooldataform.formmodel.concreta.*;
import tooldataform.formmodel.containers.GraphicalContainer;
import tooldataform.*;

public class GenerateCode {
	
	ModelFactory modelFactoryDF = null;
	gestionmodelosconsultas.ModelFactory modelFactoryMC = null;
	whoownme.model.ModelFactoryModel modelFactoryModel = whoownme.model.ModelFactoryModel.getInstance();
	ArrayList<String> namesViewModels = new ArrayList<String>();

	public GenerateCode() {

		inicializarDF();
		inicializarMC();
	}
	
	public void inicializarDF() {
		
		org.eclipse.emf.common.util.URI uri = org.eclipse.emf.common.util.URI.createURI("platform:/resource/GestionConsultas/modelos/model.tooldataform");
		org.eclipse.emf.ecore.resource.ResourceSet resourceSet= new org.eclipse.emf.ecore.resource.impl.ResourceSetImpl();
		org.eclipse.emf.ecore.resource.Resource resource = resourceSet.createResource(uri);
		try {
			resource.load(java.util.Collections.EMPTY_MAP);
			modelFactoryDF = (ModelFactory) resource.getContents().get(0);
		} catch (java.io.IOException e) {
			
			// TO-DO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void inicializarMC() {
		
		org.eclipse.emf.common.util.URI uri = org.eclipse.emf.common.util.URI.createURI("platform:/resource/GestionConsultas/modelos/produccion.gestionmodelosconsultas");
		org.eclipse.emf.ecore.resource.ResourceSet resourceSet= new org.eclipse.emf.ecore.resource.impl.ResourceSetImpl();
		org.eclipse.emf.ecore.resource.Resource resource = resourceSet.createResource(uri);
		try {
			resource.load(java.util.Collections.EMPTY_MAP);
			modelFactoryMC = (gestionmodelosconsultas.ModelFactory) resource.getContents().get(0);
		} catch (java.io.IOException e) {
			
			// TO-DO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void getNamesViewModel() {
		
		Interface interface1 = modelFactoryDF.getListProyecto().get(0).getListDataFormDiagram().get(0).getTheInterface();
		
		GraphicalContainer gC;
		String name;
		
		for (int i = 0; i < interface1.getListGraphicalContainer().size(); i++) {
			
			gC = interface1.getListGraphicalContainer().get(i);
			name = gC.getName();
			if (name.contains("Table")) {
				namesViewModels.add(name);
			}
		}
		
	}
	
	
	public void crearViewModels() {
		
		EList<EADiagram> listaViewModel = modelFactoryMC.getFactoryModeloConsultas().getListModeloConsulta().get(0).getListEADiagram();
		EADiagram vm = null;
		
		for (int i = 0; i < namesViewModels.size(); i++) {
			
			vm = listaViewModel.get(0);
			vm = gestionmodelosconsultas.modeloconsultas.model.ModelFactory.eINSTANCE.createViewModel();
			
			if (namesViewModels.get(i).contains("Table")) {
				
				Documento d = QueFactory.eINSTANCE.createDocumento();
				d.setOrder(1);
				vm.getListElementoConsulta().add(d);
			}
			
			listaViewModel.add(vm);
		}
		
		modelFactoryMC.salvar();
	}
	
	public void compilarConsulta() {
		
		modelFactoryMC = modelFactoryMC.cargar();
		modelFactoryModel.setModelFactory(modelFactoryMC);
		CompiladorProyeccion compiladorProyeccion = new CompiladorProyeccion();
		compiladorProyeccion.compilarProyeccion(modelFactoryMC);
	}
	
	public void copiarResultado() {
		
		Resultado result = modelFactoryMC.getFactoryModeloConsultas().getListModeloConsulta().get(0).getListResultado().get(0);
		model.ModelFactory modelFactory = model.ModelFactory.eINSTANCE;
		FactoryModel factoryModel = modelFactory.createFactoryModel();
		factoryModel = factoryModel.cargar();
		DocumentoImpl d = null;
		String id, tipo, hora;
		
		for (int i = 0; i < result.getListResultElement().size(); i++) {
			
			d = (DocumentoImpl) result.getListResultElement().get(i);
			hora = d.getFechaCreacion().split(" ")[1];
			tipo = d.getTipo();
			id = d.getKey();
			
			MemorandoViewModel m= UimanagerFactory.eINSTANCE.createMemorandoViewModel();
			m.setHora(hora);
			m.setId(id);
			m.setTipo(tipo);
			factoryModel.getTheUIManager().getTheVentanaMemorandoViewModel().getTheContenedorMemorando().getListMemorando().add(m);
		}
		factoryModel.salvar();
		pruebamemorandos.view.view.ModelFactoryModel model = pruebamemorandos.view.view.ModelFactoryModel.getInstance();
	    model.update();
	    VentanaMemorandoViewPart ventanaMemorandoViewPart = model.getVentanaMemorandoViewPart();
	    ventanaMemorandoViewPart.updateData();
	}
		
}