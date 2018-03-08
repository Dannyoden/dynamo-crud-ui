package com.ocs.gts.ui;

import com.ocs.dynamo.ui.composite.type.AttributeGroupMode;
import com.vaadin.data.Container;
import com.vaadin.data.util.filter.Like;
import org.springframework.beans.factory.annotation.Autowired;

import com.ocs.dynamo.domain.model.EntityModel;
import com.ocs.dynamo.ui.composite.form.FormOptions;
import com.ocs.dynamo.ui.composite.layout.SimpleSearchLayout;
import com.ocs.dynamo.ui.container.QueryType;
import com.ocs.dynamo.ui.view.BaseView;
import com.ocs.gts.domain.Organization;
import com.ocs.gts.service.OrganizationService;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Layout;

import java.util.HashMap;
import java.util.Map;

@UIScope
@SpringView(name = Views.ORGANIZATION_VIEW)
public class OrganizationView extends BaseView {

	@Autowired
	private OrganizationService organizationService;

	private static final long serialVersionUID = 3310122000037867336L;

	@Override
	public void enter(ViewChangeEvent event) {
		Layout main = initLayout();

		EntityModel<Organization> em = getModelFactory().getModel(Organization.class);
		FormOptions fo = new FormOptions().setShowEditButton(true);
		fo.setShowEditButton(true).setAttributeGroupMode(AttributeGroupMode.TABSHEET).isShowToggleButton();
		SimpleSearchLayout<Integer, Organization> layout = new SimpleSearchLayout<Integer, Organization>(organizationService, em,
		        QueryType.ID_BASED, fo, null) {};


		Map<String, Container.Filter> fieldFilters = new HashMap<>();
		fieldFilters.put("countryOfOrigin", new Like("name", "%au%", false));layout.setFieldFilters(fieldFilters);
		main.addComponent(layout);
	}
}
