/**
 */
package gestionmodelosconsultas.modeloconsultas.quien.impl;

import gestionmodelosconsultas.modeloconsultas.model.impl.ElementoConsultaImpl;

import gestionmodelosconsultas.modeloconsultas.quien.Quien;
import gestionmodelosconsultas.modeloconsultas.quien.QuienPackage;

import gestionmodelosconsultas.sysinfo.data.docmgt.DocmgtPackage;
import gestionmodelosconsultas.sysinfo.data.docmgt.Documento;

import gestionmodelosconsultas.sysinfo.people.Contacto;
import gestionmodelosconsultas.sysinfo.people.PeoplePackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Quien</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link gestionmodelosconsultas.modeloconsultas.quien.impl.QuienImpl#getContacto <em>Contacto</em>}</li>
 *   <li>{@link gestionmodelosconsultas.modeloconsultas.quien.impl.QuienImpl#getTheDocumento <em>The Documento</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class QuienImpl extends ElementoConsultaImpl implements Quien {
	/**
	 * The cached value of the '{@link #getContacto() <em>Contacto</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContacto()
	 * @generated
	 * @ordered
	 */
	protected Contacto contacto;

	/**
	 * The cached value of the '{@link #getTheDocumento() <em>The Documento</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTheDocumento()
	 * @generated
	 * @ordered
	 */
	protected EList<Documento> theDocumento;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected QuienImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return QuienPackage.Literals.QUIEN;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Contacto getContacto() {
		if (contacto != null && contacto.eIsProxy()) {
			InternalEObject oldContacto = (InternalEObject)contacto;
			contacto = (Contacto)eResolveProxy(oldContacto);
			if (contacto != oldContacto) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, QuienPackage.QUIEN__CONTACTO, oldContacto, contacto));
			}
		}
		return contacto;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Contacto basicGetContacto() {
		return contacto;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetContacto(Contacto newContacto, NotificationChain msgs) {
		Contacto oldContacto = contacto;
		contacto = newContacto;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, QuienPackage.QUIEN__CONTACTO, oldContacto, newContacto);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setContacto(Contacto newContacto) {
		if (newContacto != contacto) {
			NotificationChain msgs = null;
			if (contacto != null)
				msgs = ((InternalEObject)contacto).eInverseRemove(this, PeoplePackage.CONTACTO__QUIEN, Contacto.class, msgs);
			if (newContacto != null)
				msgs = ((InternalEObject)newContacto).eInverseAdd(this, PeoplePackage.CONTACTO__QUIEN, Contacto.class, msgs);
			msgs = basicSetContacto(newContacto, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, QuienPackage.QUIEN__CONTACTO, newContacto, newContacto));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Documento> getTheDocumento() {
		if (theDocumento == null) {
			theDocumento = new EObjectWithInverseResolvingEList.ManyInverse<Documento>(Documento.class, this, QuienPackage.QUIEN__THE_DOCUMENTO, DocmgtPackage.DOCUMENTO__THE_ROL);
		}
		return theDocumento;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case QuienPackage.QUIEN__CONTACTO:
				if (contacto != null)
					msgs = ((InternalEObject)contacto).eInverseRemove(this, PeoplePackage.CONTACTO__QUIEN, Contacto.class, msgs);
				return basicSetContacto((Contacto)otherEnd, msgs);
			case QuienPackage.QUIEN__THE_DOCUMENTO:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getTheDocumento()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case QuienPackage.QUIEN__CONTACTO:
				return basicSetContacto(null, msgs);
			case QuienPackage.QUIEN__THE_DOCUMENTO:
				return ((InternalEList<?>)getTheDocumento()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case QuienPackage.QUIEN__CONTACTO:
				if (resolve) return getContacto();
				return basicGetContacto();
			case QuienPackage.QUIEN__THE_DOCUMENTO:
				return getTheDocumento();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case QuienPackage.QUIEN__CONTACTO:
				setContacto((Contacto)newValue);
				return;
			case QuienPackage.QUIEN__THE_DOCUMENTO:
				getTheDocumento().clear();
				getTheDocumento().addAll((Collection<? extends Documento>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case QuienPackage.QUIEN__CONTACTO:
				setContacto((Contacto)null);
				return;
			case QuienPackage.QUIEN__THE_DOCUMENTO:
				getTheDocumento().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case QuienPackage.QUIEN__CONTACTO:
				return contacto != null;
			case QuienPackage.QUIEN__THE_DOCUMENTO:
				return theDocumento != null && !theDocumento.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == gestionmodelosconsultas.sysinfo.people.Quien.class) {
			switch (derivedFeatureID) {
				case QuienPackage.QUIEN__CONTACTO: return PeoplePackage.QUIEN__CONTACTO;
				case QuienPackage.QUIEN__THE_DOCUMENTO: return PeoplePackage.QUIEN__THE_DOCUMENTO;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == gestionmodelosconsultas.sysinfo.people.Quien.class) {
			switch (baseFeatureID) {
				case PeoplePackage.QUIEN__CONTACTO: return QuienPackage.QUIEN__CONTACTO;
				case PeoplePackage.QUIEN__THE_DOCUMENTO: return QuienPackage.QUIEN__THE_DOCUMENTO;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

} //QuienImpl