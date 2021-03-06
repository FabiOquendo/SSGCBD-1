/**
 */
package gestionmodelosconsultas.modeloconsultas.model.util;

import gestionmodelosconsultas.modeloconsultas.model.*;

import gestionmodelosconsultas.sysinfo.core.ElementoModelo;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see gestionmodelosconsultas.modeloconsultas.model.ModelPackage
 * @generated
 */
public class ModelAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static ModelPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = ModelPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ModelSwitch<Adapter> modelSwitch =
		new ModelSwitch<Adapter>() {
			@Override
			public Adapter caseRelacion(Relacion object) {
				return createRelacionAdapter();
			}
			@Override
			public Adapter caseCampo(Campo object) {
				return createCampoAdapter();
			}
			@Override
			public Adapter caseEADiagram(EADiagram object) {
				return createEADiagramAdapter();
			}
			@Override
			public Adapter caseViewModel(ViewModel object) {
				return createViewModelAdapter();
			}
			@Override
			public Adapter caseElementoConsulta(ElementoConsulta object) {
				return createElementoConsultaAdapter();
			}
			@Override
			public Adapter caseProyeccion(Proyeccion object) {
				return createProyeccionAdapter();
			}
			@Override
			public Adapter caseElementoModelo(ElementoModelo object) {
				return createElementoModeloAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link gestionmodelosconsultas.modeloconsultas.model.Relacion <em>Relacion</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see gestionmodelosconsultas.modeloconsultas.model.Relacion
	 * @generated
	 */
	public Adapter createRelacionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gestionmodelosconsultas.modeloconsultas.model.Campo <em>Campo</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see gestionmodelosconsultas.modeloconsultas.model.Campo
	 * @generated
	 */
	public Adapter createCampoAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gestionmodelosconsultas.modeloconsultas.model.EADiagram <em>EA Diagram</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see gestionmodelosconsultas.modeloconsultas.model.EADiagram
	 * @generated
	 */
	public Adapter createEADiagramAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gestionmodelosconsultas.modeloconsultas.model.ViewModel <em>View Model</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see gestionmodelosconsultas.modeloconsultas.model.ViewModel
	 * @generated
	 */
	public Adapter createViewModelAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gestionmodelosconsultas.modeloconsultas.model.ElementoConsulta <em>Elemento Consulta</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see gestionmodelosconsultas.modeloconsultas.model.ElementoConsulta
	 * @generated
	 */
	public Adapter createElementoConsultaAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gestionmodelosconsultas.modeloconsultas.model.Proyeccion <em>Proyeccion</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see gestionmodelosconsultas.modeloconsultas.model.Proyeccion
	 * @generated
	 */
	public Adapter createProyeccionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gestionmodelosconsultas.sysinfo.core.ElementoModelo <em>Elemento Modelo</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see gestionmodelosconsultas.sysinfo.core.ElementoModelo
	 * @generated
	 */
	public Adapter createElementoModeloAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //ModelAdapterFactory
