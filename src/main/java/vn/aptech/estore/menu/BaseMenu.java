package vn.aptech.estore.menu;

import vn.aptech.estore.constant.Constant;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public abstract class BaseMenu {
    protected String title;
    protected Map<Integer, String> menuItems;
    protected Scanner scanner;
    private int maxLength = 50;

    public BaseMenu(String title) {
        this.title = title;
        this.menuItems = new LinkedHashMap<>();
        this.scanner = new Scanner(System.in, "UTF-8");
    }

    public String valueOf(int choice) {
        return this.menuItems.get(choice);
    }

    public void printMenuHeader() {
        System.out.println("******************************************************");
        System.out.println(title.toUpperCase());
        System.out.println("******************************************************");
        for (Map.Entry<Integer, String> item : menuItems.entrySet()) {
            System.out.println(item.getKey() + ") " + item.getValue());
        }
    }

    protected int enterChoice() {
        System.out.println("Nhập lựa chọn của bạn [0-Thoát]: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }

    protected String enterString(String title) {
        System.out.println(title);
        return scanner.nextLine();
    }

    protected String enterString(String title, boolean required) {
        String str;
        do {
            System.out.println(title);
            str = scanner.nextLine();
            if (str.trim().equals("") && required) {
                System.out.println(Constant.Response.OBJECT_REQUIRED);
            }
        } while (str.equals(""));
        return str;
    }

    protected void showMsg(Constant.MESSAGE_TYPE type, String msg) {
        printDivider();
        System.out.printf(">> %s: %s\n", type.toString(), msg);
        printDivider();
    }

    protected void showTable(List<String> columns, List<Object> rows) {
        StringBuilder header = new StringBuilder();
        for (String column : columns) {
            if (column.length() > maxLength) {
                maxLength = column.length();
            }
        }
        for (Object row : rows) {
            if (row.toString().length() > maxLength) {
                maxLength = row.toString().length();
            }
        }
        for (String column : columns) {
            if (column.length() > maxLength) {
                maxLength = column.length();
            }
            header.append(String.format("%-" + (Math.max(column.length(), 10)) + "s", column));
        }
        System.out.println(header);
        StringBuilder output = new StringBuilder();
        for (Object row : rows) {
            if (row.toString().length() > maxLength) {
                maxLength = row.toString().length();
            }
            output.append(String.format("%-" + (Math.max(row.toString().length(), 10)) + "s", row));
        }
        System.out.println(output);
    }

    public void printDivider() {
        System.out.println("------------------------------------------------------");
    }

    public abstract void start();
}
