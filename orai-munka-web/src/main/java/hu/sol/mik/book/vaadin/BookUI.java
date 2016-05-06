package hu.sol.mik.book.vaadin;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.Buffered.SourceException;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.BeanItem;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ShortcutAction;
import com.vaadin.event.ShortcutListener;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnGenerator;
import com.vaadin.ui.TableFieldFactory;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

import hu.sol.mik.book.bean.Book;
import hu.sol.mik.book.dao.BookDao;
import hu.sol.mik.book.dao.impl.BookDaoImpl;

public class BookUI extends UI {

	private BeanContainer<Long, Book> bookDataSource;
	private BookDao bookDao;
	protected Window editBookWindow;
	protected List<Book> selectedBooks;
	protected Object editItemId;
	protected Object editPropertyId;

	@Override
	protected void init(VaadinRequest request) {
		selectedBooks = new ArrayList<Book>();
		bookDao = new BookDaoImpl();
		bookDataSource = new BeanContainer<Long, Book>(Book.class);
		bookDataSource.setBeanIdProperty("id");
		bookDataSource.addAll(bookDao.listAll());
		this.setContent(createBookVerticalLayout());
	}

	private Component createBookVerticalLayout() {
		VerticalLayout verticalLayout = new VerticalLayout();
		verticalLayout.setSpacing(true);
		verticalLayout.setMargin(true);
		verticalLayout.addComponent(createBookTable());
		verticalLayout.addComponent(createFunctionLayout());
		return verticalLayout;
	}

	private Component createFunctionLayout() {
		HorizontalLayout horizontalLayout = new HorizontalLayout();
		horizontalLayout.setMargin(true);
		horizontalLayout.setSpacing(true);

		Button newBookButton = new Button("Új könyv felvitel");
		newBookButton.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				Book book = new Book();
				openEditWindow(book, "Új könyv felvitel");
			}
		});

		Button deleteSelectedBooksButton = new Button("Kiválasztottak törlése");
		deleteSelectedBooksButton.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				for (Book book : selectedBooks) {
					bookDao.delete(book);
				}
				selectedBooks.clear();
				refreshTable();

			}
		});

		horizontalLayout.addComponent(newBookButton);
		horizontalLayout.addComponent(deleteSelectedBooksButton);
		return horizontalLayout;
	}

	private void openEditWindow(Book book, String title) {
		editBookWindow = new Window(title);
		editBookWindow.center();
		editBookWindow.setContent(createBookEditLayout(book));
		this.addWindow(editBookWindow);
	}

	protected Component createBookEditLayout(Book book) {
		VerticalLayout verticalLayout = new VerticalLayout();
		final BeanFieldGroup<Book> bookBinder = new BeanFieldGroup<Book>(Book.class);
		bookBinder.setItemDataSource(book);
		verticalLayout.addComponent(createBookEditForm(bookBinder));
		Button saveButton = new Button("Mentés");
		saveButton.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				try {
					bookBinder.commit();
					Book bean = bookBinder.getItemDataSource().getBean();
					saveOrUpdateBook(bean);
					editBookWindow.close();
				} catch (CommitException e) {
					Notification.show("Hiba történt");
				}
			}
		});
		verticalLayout.addComponent(saveButton);
		verticalLayout.setMargin(true);
		return verticalLayout;
	}

	private void refreshTable() {
		bookDataSource.removeAllItems();
		bookDataSource.addAll(bookDao.listAll());
	}

	private Component createBookEditForm(BeanFieldGroup<Book> bookBinder) {
		FormLayout formLayout = new FormLayout();
		TextField titleField = bookBinder.buildAndBind("Cím", "title", TextField.class);
		titleField.setNullRepresentation("");
		formLayout.addComponent(titleField);
		TextField descField = bookBinder.buildAndBind("Leírás", "description", TextField.class);
		descField.setNullRepresentation("");
		formLayout.addComponent(descField);
		TextField authorField = bookBinder.buildAndBind("Szerző", "author", TextField.class);
		authorField.setNullRepresentation("");
		formLayout.addComponent(authorField);
		TextField pubYearField = bookBinder.buildAndBind("Publikáció", "pubYear", TextField.class);
		formLayout.addComponent(pubYearField);
		return formLayout;
	}

	private Component createBookTable() {
		final Table table = new Table("Könyvek listája");
		table.setSizeFull();
		table.setContainerDataSource(bookDataSource);
		table.setVisibleColumns("title", "description", "author", "pubYear");
		table.addGeneratedColumn("selectBook", new ColumnGenerator() {
			@Override
			public Object generateCell(final Table source, final Object itemId, final Object columnId) {
				final CheckBox selectCheckBox = new CheckBox();
				selectCheckBox.addValueChangeListener(new ValueChangeListener() {

					@Override
					public void valueChange(ValueChangeEvent event) {
						BeanItem<Book> beanItem = (BeanItem<Book>) source.getItem(itemId);
						if (selectCheckBox.getValue()) {
							selectedBooks.add(beanItem.getBean());
						} else {
							selectedBooks.remove(beanItem.getBean());
						}
					}
				});
				return selectCheckBox;
			}
		});

		table.setColumnHeader("title", "Cím");
		table.setColumnHeader("description", "Leírás");
		table.setColumnHeader("author", "Szerző");
		table.setColumnHeader("pubYear", "Publikálás éve");
		table.setColumnHeader("selectBook", "Kiválasztás");

		table.addItemClickListener(new ItemClickListener() {
			@Override
			public void itemClick(ItemClickEvent event) {
				editItemId = event.getItemId();
				editPropertyId = event.getPropertyId();
				table.refreshRowCache();
			}
		});

		table.setEditable(true);
		table.setTableFieldFactory(new TableFieldFactory() {
			@Override
			public Field<?> createField(Container container, Object itemId, Object propertyId, Component uiContext) {
				if (propertyId.equals(editPropertyId) && itemId.equals(editItemId)) {
					return createEditTextField(table, itemId, propertyId);
				}
				return null;
			}
		});

		return table;
	}

	private TextField createEditTextField(Table table, Object itemId, Object propertyId) {
		Property editProperty = table.getContainerProperty(itemId, propertyId);
		TextField textField = new TextField(editProperty);
		textField.setNullRepresentation("");
		textField.setBuffered(true);
		textField.focus();
		String editStartValue = textField.getValue();
		if (editStartValue != null) {
			textField.setCursorPosition(textField.getValue().length());
		}

		textField.addShortcutListener(
				new ShortcutListener("EnterOnTextAreaShorcut", ShortcutAction.KeyCode.ESCAPE, null) {
					@Override
					public void handleAction(Object sender, Object target) {
						editItemId = null;
						editPropertyId = null;
						table.refreshRowCache();
					}
				});

		textField.addShortcutListener(
				new ShortcutListener("EnterOnTextAreaShorcut", ShortcutAction.KeyCode.ENTER, null) {
					@Override
					public void handleAction(Object sender, Object target) {
						String currentValue = textField.getValue();
						if (currentValue == null ? editStartValue != null : !currentValue.equals(editStartValue)) {
							try {
								textField.commit();
								BeanItem<Book> beanItem = (BeanItem<Book>) table.getItem(editItemId);
								saveOrUpdateBook(beanItem.getBean());
							} catch (InvalidValueException exception) {
								Notification.show("Hibás bemenet");
							} catch (SourceException exception) {
								Notification.show("Hiba történt");

							}
						}
						editItemId = null;
						editPropertyId = null;
						table.refreshRowCache();
					}
				});

		return textField;
	}

	private void saveOrUpdateBook(Book book) {
		if (book.getId() == null) {
			bookDao.saveBook(book);
			refreshTable();
		} else {
			bookDao.updateBook(book);
		}
		Notification.show("Sikeres felvitel");
	}
}
