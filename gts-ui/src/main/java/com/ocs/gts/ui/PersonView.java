package com.ocs.gts.ui;

import com.ocs.dynamo.domain.model.EntityModel;
import com.ocs.dynamo.ui.composite.form.FormOptions;
import com.ocs.dynamo.ui.composite.layout.ServiceBasedSplitLayout;
import com.ocs.dynamo.ui.composite.type.ScreenMode;
import com.ocs.dynamo.ui.view.BaseView;
import com.ocs.gts.domain.Person;
import com.ocs.gts.service.PersonService;
import com.vaadin.data.Container;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import com.vaadin.data.util.filter.Like;




@UIScope
@SpringView(name = Views.PERSON_VIEW)
public class PersonView extends BaseView {

	private static final long serialVersionUID = 5368745165020200786L;

	@Autowired
	private PersonService personService;


	protected Like constructQuickSearchFilter(String value) {
		return new Like("firstName", "%" + value + "%", false);
	}


	@Override
	public void enter(ViewChangeEvent event) {
		Layout main = initLayout();

		EntityModel<Person> em = getModelFactory().getModel(Person.class);
		FormOptions fo = new FormOptions().setScreenMode(ScreenMode.HORIZONTAL);
		fo.setShowRemoveButton(true).setShowQuickSearchField(true);
		ServiceBasedSplitLayout<Integer, Person> layout = new ServiceBasedSplitLayout<Integer, Person>(personService, em, fo, null) {

			private static final long serialVersionUID = -4747417585679227639L;

			protected Container.Filter constructQuickSearchFilter(String value) {
				return new Like("firstName", "%" + value + "%", false);
			}

			@Override
			protected void postProcessButtonBar(Layout buttonBar) {
				Button notificationButton = new Button("Show name");
				registerButton(notificationButton);
				notificationButton.addClickListener(new Button.ClickListener() {

					@Override
					public void buttonClick(Button.ClickEvent event) {
						Notification.show(getSelectedItem().getFullName(), Notification.Type.ERROR_MESSAGE);

					}
				});
				buttonBar.addComponent(notificationButton);
			}

		};
		main.addComponent(layout);
	}
}
