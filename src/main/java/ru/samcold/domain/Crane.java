package ru.samcold.domain;

public class Crane {
    private String name;        // наименование, тип
    private String mark;        // марка
    private String mode;        // исполнение
    private String zav;         // зав.№
    private String reg;         // рег.№
    private String factory;     // завод-изготовитель
    private String issue;       // год выпуска
    private String capacity;    // грузоподъемность
    private String lifting;     // высота подъема
    private String span;        // пролет (вылет)

    public Crane() {
    }

    public Crane(String name, String mark, String mode, String zav, String reg, String factory, String issue, String capacity, String lifting, String span) {
        this.name = name;
        this.mark = mark;
        this.mode = mode;
        this.zav = zav;
        this.reg = reg;
        this.factory = factory;
        this.issue = issue;
        this.capacity = capacity;
        this.lifting = lifting;
        this.span = span;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getZav() {
        return zav;
    }

    public void setZav(String zav) {
        this.zav = zav;
    }

    public String getReg() {
        return reg;
    }

    public void setReg(String reg) {
        this.reg = reg;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getLifting() {
        return lifting;
    }

    public void setLifting(String lifting) {
        this.lifting = lifting;
    }

    public String getSpan() {
        return span;
    }

    public void setSpan(String span) {
        this.span = span;
    }

    @Override
    public String toString() {
        return "Crane{" +
                "name='" + name + '\'' +
                ", mark='" + mark + '\'' +
                ", mode='" + mode + '\'' +
                ", zav='" + zav + '\'' +
                ", reg='" + reg + '\'' +
                ", factory='" + factory + '\'' +
                ", issue='" + issue + '\'' +
                ", capacity='" + capacity + '\'' +
                ", lifting='" + lifting + '\'' +
                ", span='" + span + '\'' +
                '}';
    }
}
