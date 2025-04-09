package com.webecc.objects;

public class TextschluesselObjectClass {
    // === Daten ===
    // === Header ===
    private int textschluesselId;
    private String text;
    private String anlage;

    // === Grunddaten ===
    private String letzteAenderungGrunddaten;
    private String textart;
    private boolean uebersetzungsrelevant;
    private String sedokKlassifizierung;
    private String systemKennzeichen;
    private String oberBegriff;
    private boolean darfNichtGeloeschtWerden;
    private boolean steuerndInMantis;
    private boolean sperreFuerNeuanwendung;

    // === Sprachabhängige Daten - Deutsch ===
    private String letzteAenderungDerSprachabhaengigeDaten1;
    private String sprache;
    private String textDeutsch;
    private String textDeutschErsteZeile;

    // === Sprachabhängige Daten - Zweite Sprache ===
    private String letzteAenderungDerSprachabhaengigeDaten2;
    private String zweiteSprache;
    private boolean uebersetzt;
    private String textDerZweiteSprache;

    // === Getter und Setter ===
    public int getTextschluesselId() {
        return textschluesselId;
    }

    public void setTextschluesselId(int textschluesselId) {
        this.textschluesselId = textschluesselId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAnlage() {
        return anlage;
    }

    public void setAnlage(String anlage) {
        this.anlage = anlage;
    }

    public String getLetzteAenderungGrunddaten() {
        return letzteAenderungGrunddaten;
    }

    public void setLetzteAenderungGrunddaten(String letzteAenderungGrunddaten) {
        this.letzteAenderungGrunddaten = letzteAenderungGrunddaten;
    }

    public String getTextart() {
        return textart;
    }

    public void setTextart(String textart) {
        this.textart = textart;
    }

    public boolean isUebersetzungsrelevant() {
        return uebersetzungsrelevant;
    }

    public void setUebersetzungsrelevant(boolean uebersetzungsrelevant) {
        this.uebersetzungsrelevant = uebersetzungsrelevant;
    }

    public String getSedokKlassifizierung() {
        return sedokKlassifizierung;
    }

    public void setSedokKlassifizierung(String sedokKlassifizierung) {
        this.sedokKlassifizierung = sedokKlassifizierung;
    }

    public String getSystemKennzeichen() {
        return systemKennzeichen;
    }

    public void setSystemKennzeichen(String systemKennzeichen) {
        this.systemKennzeichen = systemKennzeichen;
    }

    public String getOberBegriff() {
        return oberBegriff;
    }

    public void setOberBegriff(String oberBegriff) {
        this.oberBegriff = oberBegriff;
    }

    public boolean isDarfNichtGeloeschtWerden() {
        return darfNichtGeloeschtWerden;
    }

    public void setDarfNichtGeloeschtWerden(boolean darfNichtGeloeschtWerden) {
        this.darfNichtGeloeschtWerden = darfNichtGeloeschtWerden;
    }

    public boolean isSteuerndInMantis() {
        return steuerndInMantis;
    }

    public void setSteuerndInMantis(boolean steuerndInMantis) {
        this.steuerndInMantis = steuerndInMantis;
    }

    public boolean isSperreFuerNeuanwendung() {
        return sperreFuerNeuanwendung;
    }

    public void setSperreFuerNeuanwendung(boolean sperreFuerNeuanwendung) {
        this.sperreFuerNeuanwendung = sperreFuerNeuanwendung;
    }

    public String getLetzteAenderungDerSprachabhaengigeDaten1() {
        return letzteAenderungDerSprachabhaengigeDaten1;
    }

    public void setLetzteAenderungDerSprachabhaengigeDaten1(String letzteAenderungDerSprachabhaengigeDaten1) {
        this.letzteAenderungDerSprachabhaengigeDaten1 = letzteAenderungDerSprachabhaengigeDaten1;
    }

    public String getSprache() {
        return sprache;
    }

    public void setSprache(String sprache) {
        this.sprache = sprache;
    }

    public String getTextDeutsch() {
        return textDeutsch;
    }

    public void setTextDeutsch(String textDeutsch) {
        this.textDeutsch = textDeutsch;
    }

    public String getTextDeutschErsteZeile() {
        return textDeutschErsteZeile;
    }

    public void setTextDeutschErsteZeile(String textDeutschErsteZeile) {
        this.textDeutschErsteZeile = textDeutschErsteZeile;
    }

    public String getLetzteAenderungDerSprachabhaengigeDaten2() {
        return letzteAenderungDerSprachabhaengigeDaten2;
    }

    public void setLetzteAenderungDerSprachabhaengigeDaten2(String letzteAenderungDerSprachabhaengigeDaten2) {
        this.letzteAenderungDerSprachabhaengigeDaten2 = letzteAenderungDerSprachabhaengigeDaten2;
    }

    public String getZweiteSprache() {
        return zweiteSprache;
    }

    public void setZweiteSprache(String zweiteSprache) {
        this.zweiteSprache = zweiteSprache;
    }

    public boolean isUebersetzt() {
        return uebersetzt;
    }

    public void setUebersetzt(boolean uebersetzt) {
        this.uebersetzt = uebersetzt;
    }

    public String getTextDerZweiteSprache() {
        return textDerZweiteSprache;
    }

    public void setTextDerZweiteSprache(String textDerZweiteSprache) {
        this.textDerZweiteSprache = textDerZweiteSprache;
    }
}
