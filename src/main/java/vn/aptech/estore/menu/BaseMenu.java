package vn.aptech.estore.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import vn.aptech.estore.constant.Constant;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public abstract class BaseMenu {
    protected String title;
    protected Map<Integer, String> menuItems;
    protected Scanner scanner;

    @Autowired
    private MessageSource messageSource;

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

    public void printMenuHeader(String subTitle) {
        System.out.println("******************************************************");
        System.out.println(title.toUpperCase());
        System.out.println("******************************************************");
        System.out.println(subTitle);
        System.out.println("======================================================");
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
        return str.trim();
    }

    protected int enterInteger(String title) {
        System.out.println(title);
        int num = scanner.nextInt();
        scanner.nextLine();
        return num;
    }

    protected double enterDouble(String title, boolean required) {
        double num;
        do {
            System.out.println(title);
            num = scanner.nextDouble();
            if (num == 0 && required) {
                System.out.println(Constant.Response.OBJECT_REQUIRED);
            }
        } while (num == 0);
        scanner.nextLine();
        return num;
    }

    protected double enterDouble(String title) {
        System.out.println(title);
        double num = scanner.nextDouble();
        scanner.nextLine();
        return num;
    }

    protected int enterInteger(String title, boolean required) {
        int num;
        do {
            System.out.println(title);
            num = scanner.nextInt();
            if (num == 0 && required) {
                System.out.println(Constant.Response.OBJECT_REQUIRED);
            }
        } while (num == 0);
        scanner.nextLine();
        return num;
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
