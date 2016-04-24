package investigate.jface.preference;

import org.eclipse.jface.preference.PreferenceStore;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;

import java.io.IOException;

public class PreferenceStoreTest {

	public static void main(String[] args) {
		PreferenceStore preferenceStore = new PreferenceStore("F:\\myPreference.properties");
		preferenceStore.setValue("Database", "mysql");
		preferenceStore.setValue("UserName", "Janet");
		preferenceStore.setValue("Password", "123");
		try {
			preferenceStore.save();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String database = preferenceStore.getString("Database");
		preferenceStore.addPropertyChangeListener(new IPropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent event) {
				if (event.getProperty().equals("Database")) {
					System.out.println("old value:" + event.getOldValue());
					System.out.println("new value:" + event.getNewValue());
				}
			}

		});
		preferenceStore.setValue("Database", "sqlserver");
	}
}
