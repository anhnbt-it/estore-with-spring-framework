package vn.aptech.estore.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import vn.aptech.estore.constant.Constant;

import java.util.*;

public abstract class BaseMenu {
    protected String title;
    protected Map<Integer, String> menuItems;
    protected Scanner scanner;

    @Autowired
    private MessageSource messageSource;

    public BaseMenu() {
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
        System.out.println(messageSource.getMessage("message.choice.enter", new Object[]{}, Locale.getDefault()));
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
        System.out.printf(">> %s: %s\n", type.toString(), msg);
    }

    public void printDivider() {
        System.out.println("======================================================");
    }

    public void printTitle(String title) {
        System.out.println("******************************************************");
        System.out.println(title.toUpperCase());
        System.out.println("******************************************************");
    }

    public abstract void start();
}
