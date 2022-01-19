package ru.samcold.domain;

public class Crane {
    private String name;        // наименование, тип
    private String mark;        // марка
    private String mode;        // исполнение
    private String zav;         // зав.№
    private String reg;         // рег.№
    private String factory;     // завод-изготовитель
    private int issue;          // год выпуска
    private double capacity;    // грузоподъемность
    private double lifting;     // высота подъема
    private double span;        // пролет (вылет)
    private double track;       // колея

    public Crane() {
    }

    public Crane(CraneProxy proxy) {
        name = proxy.nameProperty().get();
        mark = proxy.markProperty().get();
        mode = proxy.modeProperty().get();
        zav = proxy.zavProperty().get();
        reg = proxy.regProperty().get();
        factory = proxy.factoryProperty().get();
        issue = proxy.issueProperty().get();
        capacity = proxy.capacityProperty().get();
        lifting = proxy.liftingProperty().get();
        span = proxy.spanProperty().get();
    }

    //region getters/setters
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

    public int getIssue() {
        return issue;
    }

    public void setIssue(int issue) {
        this.issue = issue;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public double getLifting() {
        return lifting;
    }

    public void setLifting(double lifting) {
        this.lifting = lifting;
    }

    public double getSpan() {
        return span;
    }

    public void setSpan(double span) {
        this.span = span;
    }

    public double getTrack() {
        return track;
    }

    public void setTrack(double track) {
        this.track = track;
    }
    //endregion

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
                ", track='" + track + '\'' +
                '}';
    }
}
