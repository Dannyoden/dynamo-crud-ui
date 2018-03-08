package com.ocs.gts.ui;

import com.ocs.dynamo.domain.model.EntityModel;
import com.ocs.dynamo.ui.composite.form.FormOptions;
import com.ocs.dynamo.ui.composite.layout.ServiceBasedSplitLayout;
import com.ocs.dynamo.ui.composite.layout.TabularEditLayout;
import com.ocs.dynamo.ui.composite.type.ScreenMode;
import com.ocs.gts.domain.Delivery;
import com.ocs.gts.domain.Gift;
import org.springframework.beans.factory.annotation.Autowired;

import com.ocs.dynamo.ui.view.BaseView;
import com.ocs.gts.service.DeliveryService;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Layout;

@UIScope
@SpringView(name = Views.DELIVERY_VIEW)
public class DeliveryView extends BaseView {

	@Autowired
	private DeliveryService deliveryService;

	private static final long serialVersionUID = 3310122000037867336L;

	@Override
	public void enter(ViewChangeEvent event) {
		Layout main = initLayout();

		EntityModel<Delivery> em = getModelFactory().getModel(Delivery.class);
		FormOptions fo = new FormOptions().setShowRemoveButton(true);
		TabularEditLayout<Integer, Delivery> layout = new TabularEditLayout<Integer, Delivery>(deliveryService, em, fo, null);
		main.addComponent(layout);

	}
}
