package investigate.databinding;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.*;

/**
 * Created by LiuPin on 2016/5/3.
 */
public class DataBindingDemos extends ApplicationWindow {

  public DataBindingDemos() {
    super(null);
  }

  @Override protected void configureShell(Shell shell) {
    super.configureShell(shell);
    shell.setLayout(new FillLayout());
  }

  @Override protected Control createContents(Composite parent) {
    Group group = new Group(parent, SWT.NONE);
    group.setText("Radio Group with Names");
    GridLayoutFactory.fillDefaults().applyTo(group);
    GridDataFactory.fillDefaults().grab(true, true).applyTo(group);

    // Options for the radio buttons
    String[] names = new String[] { "Matthew Hall", "Christoph Zauner", "Tom Schindl", "Wim Jongman", "Dirk Fauth", "Lars Vogel",
        "Simon Scholz" };

/*    SelectObservableValue selectedRadioButtonObservable = new SelectObservableValue();
    for (String name : names) {
      Button button = new Button(group, SWT.RADIO);
      button.setText(name);
      // Add name as option value in case the appropriate button is selected
      selectedRadioButtonObservable.addOption("Selected: " + name, WidgetProperties.selection().observe(button));
    }

    Label label = new Label(shell, SWT.NONE);
    GridDataFactory.fillDefaults().applyTo(label);
    ISWTObservableValue labelTextObservable = WidgetProperties.text().observe(label);

    DataBindingContext dbc = new DataBindingContext();
    // bind label text to currently selected option
    dbc.bindValue(selectedRadioButtonObservable, labelTextObservable);*/

    return parent;
  }

  public static void main(String[] args) {
    DataBindingDemos main = new DataBindingDemos();
    main.setBlockOnOpen(true);
    main.open();
    Display.getCurrent().dispose();
  }
}
