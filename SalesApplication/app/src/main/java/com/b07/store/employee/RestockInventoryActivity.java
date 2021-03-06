package com.b07.store.employee;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.b07.R;
import com.b07.database.DatabaseSelectHelper;
import com.b07.inventory.Inventory;
import com.b07.inventory.Item;
import com.b07.users.Employee;
import java.util.HashMap;

public class RestockInventoryActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.restock_inventory);

    TextView inventoryList = findViewById(R.id.employee_restock_inventory);
    EditText itemId = findViewById(R.id.employee_restock_item_id);
    EditText itemQuantity = findViewById(R.id.employee_restock_item_quantity);

    Intent intent = getIntent();
    Employee employee = (Employee) intent.getSerializableExtra("user");

    Button restockButton = findViewById(R.id.employee_restock_item_button);
    restockButton.setOnClickListener(new RestockButtonController(this, employee));

    itemId.setTransformationMethod(null);
    itemQuantity.setTransformationMethod(null);

    Inventory inventory = DatabaseSelectHelper.getInventory(this);
    HashMap<Item, Integer> itemMap = inventory.getItemMap();

    String inventoryText = "";
    for (Item item : itemMap.keySet()) {
      inventoryText +=
          item.getId() + " - " + item.getName().replace("_", " ") + ": " + itemMap.get(item) + "\n";
    }
    inventoryList.setText(inventoryText);
  }
}
