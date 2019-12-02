package com.b07.store.admin;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import com.b07.R;
import com.b07.database.DatabaseSelectHelper;
import com.b07.inventory.Item;
import com.b07.users.Roles;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActiveAccountButtonController implements View.OnClickListener {

  private Context appContext;

  public ActiveAccountButtonController(Context context) {
    appContext = context;
  }

  @Override
  public void onClick(View v) {
    TextView accountView = ((AccountListUIActivity) appContext)
        .findViewById(R.id.admin_account_list_box);
    int customerRoleId = DatabaseSelectHelper
        .getRoleIdFromName(Roles.CUSTOMER.name(), appContext);
    List<Integer> customers = DatabaseSelectHelper.getUsersByRole(customerRoleId, appContext);
    StringBuilder account_list = new StringBuilder();
    if (customers != null) {
      for (int userId : customers) {
        List<Integer> activeAccounts = DatabaseSelectHelper.getActiveAccounts(userId, appContext);
        if (!activeAccounts.isEmpty()) {
          account_list.append("UserId ").append(userId).append(": \n")
              .append("Active accounts: \n");
          for (int activeAccount : activeAccounts) {
            HashMap<Item, Integer> account = DatabaseSelectHelper
                .getAccountDetails(activeAccount, appContext);
            account_list.append("AccountID ").append(activeAccount).append(": \n");
            for (Map.Entry<Item, Integer> pair : account.entrySet()) {
              account_list.append(pair.getKey().getName()).append(": ");
              account_list.append(pair.getValue()).append("\n");
            }
            account_list.append("--------------------------------------------\n");
          }
        }
        account_list.append("====================\n");
      }
      accountView.setText(account_list);
    }
  }
}
