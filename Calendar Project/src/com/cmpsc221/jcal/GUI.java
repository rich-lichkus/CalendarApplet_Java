/*
 * Developer: Richard Lichkus
 * Program:   Project 1 - Phase II
 * Section:   2
 * Title:     Calendar GUI
 */

package com.cmpsc221.jcal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JApplet;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

public class GUI extends JApplet implements ActionListener, ItemListener
{
    public static final String[] DAYS_OF_WEEK = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    public static final String[] DAYS_OF_MONTH = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
    public static final String[] MONTHS = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
    public static final String[] YEARS = {"1900", "1901", "1902", "1903", "1904", "1905", "1906", "1907", "1908", "1909", "1910", "1911", "1912", "1913", "1914", "1915", "1916", "1917", "1918", "1919", "1920", "1921", "1922", "1923", "1924", "1925", "1926", "1927", "1928", "1929", "1930", "1931", "1932", "1933", "1934", "1935", "1936", "1937", "1938", "1939", "1940", "1941", "1942", "1943", "1944", "1945", "1946", "1947", "1948", "1949", "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049", "2050", "2051", "2052", "2053", "2054", "2055", "2056", "2057", "2058", "2059", "2060", "2061", "2062", "2063", "2064", "2065", "2066", "2067", "2068", "2069", "2070", "2071", "2072", "2073", "2074", "2075", "2076", "2077", "2078", "2079", "2080", "2081", "2082", "2083", "2084", "2085", "2086", "2087", "2088", "2089", "2090", "2091", "2092", "2093", "2094", "2095", "2096", "2097", "2098", "2099", "2100"};
    public static final String[] HOURS = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"};
    public static final String[] MINUTES = {"00", "15", "30", "45"};
    //public static final String[] DAYS_OF_WEEK = {"S", "M", "T", "W", "Th", "F", "Sa"};
    //**************************************************************************
    
    //Login 
    private JPanel pnlLogin;
    private JPanel pnlLoginMain;
    private JLabel lblUsername;
    private JTextField txtUsername;
    private JLabel lblPassword;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JLabel lblError;
    //**************************************************************************
    //Data
    private User sampleUser;
    private Contacts curContact;
    private boolean userValidated;
    private boolean isFirstRun;
    
    private Calendar calTodaysDate;
    private SimpleDateFormat formatTodaysDate;
    private String stringTodaysDate;
    private int numberHoursDay;
    private int dayStartHour;
    private int dayEndHour;
    
    private int calInterval;
    private int previousCalInterval;
    private Calendar incDate;
    private String stringIncDate;
    private SimpleDateFormat formatIncDate;
    private Calendar curDate;
    private String stringCurDate;
    private SimpleDateFormat formatCurDate;
    private Calendar decDate;
    private String stringDecDate;
    private SimpleDateFormat formatDecDate;
    
    private JPanel pnlMain;
    //Dynamic GUI Components
    //Calendar and EventGroup
    private ArrayList<JCheckBox> cbxDynCalendars;
    private ArrayList<CbxTracker> cbxDynEventGroup;
    //ComboBox Vectors
    private Vector vectorContacts;
    private Vector vectorCalendar;
    private Vector vectorEventGroup;
    
    //**************************************************************************
    //Main Layout
    
    //**************************************************************************
    //North Objects 
    private JPanel pnlNorthMain;
    
    //**************************************************************************
    //East Objects 
    private JPanel pnlEastMain;
    //East - Agenda
    private JList agendaJList;
    private Vector agendaList;
    private Vector agendaObjList;
    private JPanel pnlEastUpperMain;
    private JPanel pnlAgenda;
    private boolean editEvent;
    private JButton btnLogout;
    private JButton agendaLoad;
    private JButton agendaDelete;
    private JScrollPane scrollAgenda;
    private int agendaLoadedIndex;
    //East - Properties
    private JLabel lblEventType;
    private JPanel pnlEastLowerMain;
    private JPanel pnlProperties;
    private JScrollPane scrollProperties;
    //East - Properties - Appointment
    private JPanel pnlAppointment;
    private JLabel lblAppStart;
    private JTextField txtAppStart;
    private JLabel lblAppEnd;
    private JTextField txtAppEnd;
    private JLabel lblAppDuration;
    private JTextField txtAppDuration;
    private JLabel lblAppLocation;
    private JTextField txtAppLocation;
    //East - Properties - DayDescriptors
    private JPanel pnlDD;
    private JLabel lblDDTitle;
    private JTextField txtDDTitle;
    private JLabel lblDDStart;
    private JTextField txtDDStart;
    private JLabel lblDDEnd;
    private JTextField txtDDEnd;
    //East - Properties - Deadline   
    private JPanel pnlDeadline;
    private JLabel lblDeadlineType;
    private JTextField txtDeadlineType;
    private JLabel lblDeadlineStart;
    private JTextField txtDeadlineStart;
    private JLabel lblDeadlineEnd;
    private JTextField txtDeadlineEnd;
    private JLabel lblDeadlinePerComp;
    private JTextField txtDeadlinePerComp;
    private JLabel lblDeadlineHrsWork;
    private JTextField txtDeadlineHrsWork;
    //East - Properties - Flight
    private JPanel pnlFlight;
    private JLabel lblFlightDeparture;
    private JTextField txtFlightDeparture;
    private JLabel lblFlightReturn;
    private JTextField txtFlightReturn;
    private JLabel lblFlightConfirmCode;
    private JTextField txtFlightConfirmCode;
    private JLabel lblFlightCheckinReminder;
    private JTextField txtFlightCheckinReminder;
    private JLabel lblFlightBoardingReminder;
    private JTextField txtFlightBoardingReminder;
    //East - Properties - Meeting
    private JPanel pnlMeeting;
    private JLabel lblMeetingStart;
    private JTextField txtMeetingStart;
    private JLabel lblMeetingEnd;
    private JTextField txtMeetingEnd;
    private JLabel lblMeetingDuration;
    private JTextField txtMeetingDuration;
    private JLabel lblMeetingType;
    private JTextField txtMeetingType;
    private JLabel lblMeetingPhoneNum;
    private JTextField txtMeetingPhoneNum;
    private JLabel lblMeetingPassword;
    private JTextField txtMeetingPassword;
    private JLabel lblMeetingAddress;
    private JTextField txtMeetingAddress;
    //East - Properties - Quick
    private JPanel pnlQuick;
    private JList jlQuickEventGroup;
    private JComboBox cbxQuickCalendar;
    private JComboBox cbStartMonth;
    private JComboBox cbStartDay;
    private JComboBox cbStartYear;
    private JComboBox cbStartHour;
    private JComboBox cbStartMinute;
    private JComboBox cbEndMonth;
    private JComboBox cbEndDay;
    private JComboBox cbEndYear;
    private JComboBox cbEndHour;
    private JComboBox cbEndMinute;
    private JTextField txtQuickLocation;
    private JTextField txtQuickTitle;
    private JTextField txtQuickNote;
    private JButton quickSaveBtn;
    private JButton quickCancelBtn;
    //East - Properties - SchoolClass
    private JPanel pnlSchoolClass;
    private JLabel lblSchoolClassStart;
    private JTextField txtSchoolClassStart;
    private JLabel lblSchoolClassEnd;
    private JTextField txtSchoolClassEnd;
    private JLabel lblSchoolClassDuration;
    private JTextField txtSchoolClassDuration;
    private JLabel lblSchoolClassBuilding;
    private JTextField txtSchoolClassBuilding;
    private JLabel lblSchoolClassRoomNum;
    private JTextField txtSchoolClassRoomNum;
    //East - Properties - Vacation
    private JPanel pnlVacation;
    private JLabel lblVacationStart;
    private JTextField txtVacationStart;
    private JLabel lblVacationEnd;
    private JTextField txtVacationEnd;
    private JLabel lblVacationDuration;
    private JTextField txtVacationDuration;
    private JLabel lblVacationLocation;
    private JTextField txtVacationLocation;
    //East - Properties - Default
    private JPanel pnlPropertiesDefault;
    private JLabel lblDefault;
    //East - Properties - NewCalendar
    private JPanel pnlNewCalendar;
    private JLabel lblNewCalendarName;
    private JTextField txtNewCalendarName;
    private JButton btnNewCalendarCancel;
    private JButton btnNewCalendarSave;
    private DefaultComboBoxModel modelCalendar;
    //East - Properties - Delete Calendar
    private JPanel pnlDeleteCalendar;
    private JComboBox cbCalendarList;

    private JButton btnDeleteCalendar;
    private JButton btnCancelCalendar;
    //East - Properties - New Event Group
    private JPanel pnlAddEventGroup;
    private JComboBox cbCalendarListAddEG;
    private JLabel lblNewEGName;
    private JTextField txtNewEGName;
    private JButton btnNewEGCancel;
    private JButton btnNewEGSave;
    //East - Properties - Delete Event Group
    private JPanel pnlDeleteEventGroup;
    private JComboBox cbDeleteCalendarList;
    private JList cbEventGroupList;
    private JButton btnDeleteEG;
    private JButton btnCancelEG;
    //East - Properties = Delete Contact
    private JPanel pnlDeleteContactPanel;
    private JComboBox cbxDeleteContactList;
    private JButton btnDeleteDeleteContact;
    private JButton btnDeleteCancelContact;
    //East - Properties - Edit Contact Information
    private JLabel lblAddSuccess;
    
    private JPanel pnlEditContactPanel;
    private JPanel pnlAdd;
    private JPanel pnlEdit;
    private JPanel pnlDelete;
    private JPanel pnlAddress;
    private JTextField txtAddressLabel;
    private JTextField txtStreetNum;
    private JTextField txtStreetName;
    private JTextField txtCity;
    private JTextField txtState;
    private JTextField txtZipcode;
    private JTextField txtCountry;
    private JButton btnSaveAddress;
    private JButton btnDeleteAddress;
    private JComboBox cmbEditTitlesAddress;
    private JComboBox cmbDeleteTitlesAddress;
    
    private int deleteSelectedIndex;
    private JPanel pnlPhoneNumber;
    private JTextField txtPhoneLabel;
    private JTextField txtPhoneNumber;
    private JTextField txtPhoneExtension;
    private JButton btnSavePhone;
    private JButton btnCancelPhone;
    private JButton btnDeletePhone;
    
    private JPanel pnlEmail;
    private JTextField txtEmailLabel;
    private JTextField txtEmail;
    private JButton btnSaveEmail;
    private JButton btnDeleteEmail;
    private JComboBox cmbEditTitlesEmail;
    private JComboBox cmbDeleteTitlesEmail;
    
    private JPanel pnlSocialMedia;
    private JTextField txtSMLabel;
    private JTextField txtSM;
    private JButton btnSaveSM;
    private JButton btnDeleteSM;
    private JComboBox cmbEditTitlesSM;
    private JComboBox cmbDeleteTitlesSM;
    
    private JPanel pnlCustom;
    private JTextField txtCustomLabel;
    private JTextField txtCustomBody;
    private JButton btnSaveCustom;
    private JButton btnDeleteCustom;
    private JComboBox cmbEditTitlesCustom;
    private JComboBox cmbDeleteTitlesCustom;
    
    private JList jlEditPhoneTitles;
    private JList jlDeletePhoneTitles;
    private JList jlEditTitlesOfType;
    private Vector vectorListofTitleofType;
    private Vector vectorAddressTitles;
    private Vector vectorPhoneTitles;
    private JComboBox cmbEditTitlesPhone;
    private JComboBox cmbDeleteTitlesPhone;
    private Vector vectorSocialTitles;
    private Vector vectorEmailTitles;
    private Vector vectorCustomTitles;
    //**************************************************************************
    //South Objects 
    private JPanel pnlSouthMain;
    
    //**************************************************************************
    //West Objects 
    private JPanel pnlWestMain;
    //West - Calendars
    private JPanel pnlWestUpperMain;
    private JPanel pnlCalTitle;
    private JPanel pnlCalTree;
    private JScrollPane scrollCalTree;
    private JCheckBox cbxUserCalendars;
    private JCheckBox cbxUserEventGroup;
    private JPanel pnlWestUpperSouth;
    private JPanel pnlCalBtns;
    private JButton btnAddCal;
    private JButton btnDeleteCal;
    private JButton btnAddEGroup;
    private JButton btnDeleteEGroup;
    //West - Contacts
    private JPanel pnlWestLowerMain;
    private JPanel pnlContact;
    private DefaultComboBoxModel model;
    private JComboBox cbContactList;
    private JPanel pnlContactInfo;
    private JLabel lblName;
    private JLabel lblPhoneNumbers;
    private JLabel lblAddress;
    private JTextArea lblEmails;
    private JLabel lblSocialMedia;
    private JLabel lblCustomList;
    private JScrollPane scrollContactInfo;
    private JFrame frmEditContact;
    private JPanel pnlEditContact;
    private JButton btnSaveContact;
    private Boolean boolNewContact;
    private Boolean boolEditContact;
    private int indexSelectedContact;
    private JButton btnCancelContact;
    private JPanel pnlFrmMainContact;
    private JPanel pnlFrmUpperContact;
    private JPanel pnlFrmLowerContact;
    private JLabel lblPrefix;
    private JTextField txtPrefix;
    private JLabel lblFirstName;
    private JTextField txtFirstName;
    private JLabel lblNickName;
    private JTextField txtNickName;
    private JLabel lblMiddleName;
    private JTextField txtMiddleName;
    private JLabel lblLastName;
    private JTextField txtLastName;
    private JLabel lblSuffix;
    private JTextField txtSuffix;
    private JLabel lblJobTitle;
    private JTextField txtJobTitle;
    private JLabel lblDepartment;
    private JTextField txtDepartment;
    private JLabel lblCompany;
    private JTextField txtCompany;
    private JLabel lblBirthDate;
    private JTextField txtBirthDate;
    private JPanel pnlContactBtns;
    private JButton btnAddContact;
    private JButton btnRemoveContact;
    private JButton btnEditContact;
    private JTable tblAddress;
    private JTable tblPhoneNumber;
    private JTable tblEmail;
    private JTable tblSocialMedia;
    private JTable tblCustomList;
    
    private JScrollPane scrollAddress;
    private JScrollPane scrollPhoneNumber;
    private JScrollPane scrollEmail;
    private JScrollPane scrollSocialMedia;
    private JScrollPane scrollCustomList;
    
    //**************************************************************************
    //Center Objects 
    private JPanel pnlCenterMain;
    //Center - North
    private JPanel pnlCenterNorth;
    //Center - North - Left
    private JPanel pnlCenterNorthLeft;
    private JButton btnAddEvent;
    private JButton btnToday;
    //Center - North - Middle
    private JPanel pnlCenterNorthMiddle;
    private JLabel lblTodaysDate;
    //Center - North - Right
    private JPanel pnlCenterNorthRight;
    private JButton btnDay;
    private JButton btnWeek;
    private JButton btnMultiWeek;
    private JButton btnMonth;
    private JButton btnYear;
    //Center - Center
    private JPanel pnlCenterCenter;
    private JPanel pnlCenterCenterDay;
    private JPanel pnlCenterCenterWeek;
    private JPanel pnlCenterCenterMWeek;
    private JPanel pnlCenterCenterMonth;
    private JPanel pnlCenterCenterYear;
    private GridBagLayout yearLayout;
    //Center - South
    private JPanel pnlCenterSouth;
    private JButton btnIncDate;
    private JButton btnDecDate;
    private JLabel lblIncDate;
    private JLabel lblCurrDate;
    private JLabel lblDecDate;
    private int tabIndex;
    private JTabbedPane paneContactTabs;
    private JButton btnTabChanged;
    private ChangeListener changeListener;
    private JComboBox cbxContactInfoType;
    private JComboBox cmbDeleteTitlesOfType;
    private JComboBox cmbEditTitlesOfType;
    private boolean tabAddSelected;
    private boolean tabEditSelected;
    private boolean tabDeleteSelected;
    private JcalSQL database;
    private boolean deleteCals;
    private boolean deleteContacts;
    private boolean uploadContacts;
    
    
    @Override
    public void init()                         
    {    
        isFirstRun = true;
        database = new JcalSQL();
        
        //**********************************************************************
        //*
        //*     Temp User Data
        //* 
        //**********************************************************************
        //User
        //userValidated = true;  // <**Login**> 
        sampleUser = new User();
        int currentUserID = 1;   //<**Login**>  Create Sql call >> select userID from users where authenticated = true
        sampleUser.setUsername("sampleUser"); 
        sampleUser.setPassword("pass");
/*       
        Calendar sampleCal = Calendar.getInstance();
        Calendar sampleCal2 = Calendar.getInstance();
        Calendar sampleCal3 = Calendar.getInstance();
        Calendar sampleCal4 = Calendar.getInstance();
        Calendar sampleCal5 = Calendar.getInstance();
        Calendar sampleCal6 = Calendar.getInstance();
        Calendar sampleCal7 = Calendar.getInstance();
        Calendar sampleCal8 = Calendar.getInstance();
        Calendar sampleCal9 = Calendar.getInstance();
        Calendar sampleCal10 = Calendar.getInstance();
        
        sampleCal.set(2012, Calendar.NOVEMBER, 15, 13, 0);
        sampleCal2.set(2012, Calendar.NOVEMBER, 15, 14, 0);
        sampleCal3.set(2012, Calendar.NOVEMBER, 23, 0, 0);
        sampleCal4.set(2012, Calendar.NOVEMBER, 23, 23, 59);
        sampleCal5.set(2012, Calendar.NOVEMBER, 22, 17, 0);
        sampleCal6.set(2012, Calendar.NOVEMBER, 22, 19, 0);
        sampleCal7.set(2012, Calendar.DECEMBER, 13, 13, 0);
        sampleCal8.set(2012, Calendar.DECEMBER, 13, 14, 0);
        sampleCal9.set(2012, Calendar.DECEMBER, 24, 1, 0);
        sampleCal10.set(2012, Calendar.DECEMBER, 24, 23, 0);
 */       
        //Calendars
        if ( userValidated )
        {
            database.loadUserData(sampleUser);
            if ( sampleUser.getUserCalendars().size() == 0 )
            {
                sampleUser.addCalendar("");
                deleteCals = true;
            }
            else
            {
                deleteCals = false;
            }
            if ( sampleUser.getUserContacts().size() == 0 )
            {
                sampleUser.addContact("", "","", "", "", "", "", "", "", "");
                deleteContacts = true;
                uploadContacts = false;
            }
            else
            {
                deleteContacts = false;
            }
            /*        
            sampleUser.addCalendar("PSU");
            sampleUser.addCalendarEventGroup(1, "CMPSC 221");
            sampleUser.addEvent(1, 1, new Quick(sampleCal, sampleCal2, "220 IST", false, "Phase 2 Presentation", "Present Phase 2", true, false, 0, 15));
            sampleUser.addEvent(1, 1, new Quick(sampleCal7, sampleCal8, "220 IST", false, "Phase 3 Presentation", "Present Phase 3", true, false, 0, 15));
            sampleUser.addCalendarEventGroup(1, "THON");
            sampleUser.addCalendar("Work");
            sampleUser.addCalendarEventGroup(2,"Meetings");
            sampleUser.addCalendar("Home");
            sampleUser.addCalendarEventGroup(3,"Holidays");
            sampleUser.addEvent(3, 1, new Quick(sampleCal5, sampleCal6, "Home", false, "Thanksgiving", "Eat with the family", true, false, 0, 30));
            sampleUser.addEvent(3, 1, new Quick(sampleCal9, sampleCal10, "Home", false, "Christmas", "Open presents", true, false, 0, 30));
            sampleUser.addCalendarEventGroup(3,"Misc");
            sampleUser.addEvent(3, 2, new Quick(sampleCal3, sampleCal4, "Stores", false, "Black Friday", "Rush the stores", true, false, 0, 15));
            //Contacts
            sampleUser.addContact("Dr.", "Robert","Bob", "John", "Smith", "III", "CEO", "Corporate","Crisp Dev Labs");
            sampleUser.addContactAddress(1, "Work", 10, "Beaver Avenue", "State College", "PA", 16801, "USA");
            sampleUser.addContactPhoneNum(1, "Work", "814-880-1234", 0);
            sampleUser.addContactEmail(1, "Work", "Robert.Smith@crisp.com");
            
            sampleUser.addContact("Mr.", "Jason","Jay", "Bryan", "Scott", "", "","", "");
            sampleUser.addContactPhoneNum(2,"Mobile","814-777-6789", 0);
            sampleUser.addContactSocialMedia(2, "Twitter", "@JaysTimes");
            sampleUser.addContactCustomList(2, "Dog's Name", "Spike");
            */     
        }
 
         //<**Login**>>>
        if (!userValidated)
        {
            //**********************************************************************
            //*
            //*     Displays Login Panel Until Validated
            //* 
            //**********************************************************************
            
            database = new JcalSQL();
            pnlLoginMain = new JPanel(null);
            pnlLogin = new JPanel(new GridLayout(3,2));
            pnlLogin.setBounds(400,250,200,100);
            pnlLogin.setBorder(BorderFactory.createLoweredBevelBorder());

            lblUsername = new JLabel("Username:");
            lblUsername.setHorizontalAlignment(JLabel.CENTER); 
            txtUsername = new JTextField();
            lblPassword = new JLabel("Password:");
            lblPassword.setHorizontalAlignment(JLabel.CENTER);
            txtPassword = new JPasswordField();
            btnLogin = new JButton("Login");
            lblError = new JLabel("Try again.");
            lblError.setVisible(false);

            btnLogin.addActionListener(GUI.this);

            pnlLogin.add(lblUsername);
            pnlLogin.add(txtUsername);
            pnlLogin.add(lblPassword);
            pnlLogin.add(txtPassword);
            pnlLogin.add(btnLogin);
            pnlLogin.add(lblError);

            pnlLoginMain.add(pnlLogin);
            add(pnlLoginMain);

        }
        
         else if (userValidated) //<**Login**> if(userValidated) //
        {
            //**********************************************************************
            //*
            //*     Initialize Data and Default Settings
            //* 
            //**********************************************************************
            //Sets the calendar clock
 
            calTodaysDate = Calendar.getInstance();
            formatTodaysDate = new SimpleDateFormat("EEE, MMMMMMMMMMM dd, yyyy");
            stringTodaysDate = formatTodaysDate.format(calTodaysDate.getTime());

            dayStartHour = 8;   // Start hour for the day
            dayEndHour = 10;    // End hour for the day

            calInterval = 1;
            calInterval = 1;    // 1=D, 2=W, 3=MW, 4=M, 5=Y

            incDate = Calendar.getInstance();
            incDate.getTime();
            incDate.add(Calendar.DAY_OF_MONTH, 1);
            formatIncDate = new SimpleDateFormat("MMM dd");
            stringIncDate = formatIncDate.format(incDate.getTime());

            curDate = Calendar.getInstance();
            curDate.getTime();
            formatCurDate = new SimpleDateFormat("MMM dd, yyyy");
            stringCurDate = formatCurDate.format(curDate.getTime());

            decDate = Calendar.getInstance();
            decDate.getTime();
            decDate.add(Calendar.DAY_OF_MONTH, -1);
            formatDecDate = new SimpleDateFormat("MMM dd");
            stringDecDate = formatDecDate.format(decDate.getTime());

            //Dynamic GUI
            cbxDynCalendars = new ArrayList<JCheckBox>();
            cbxDynEventGroup = new ArrayList<CbxTracker>();
            
            vectorCalendar = new Vector();
            vectorCalendar = sampleUser.getStringCalendarList(); //database.getDBCalendarVector(currentUserID); // sampleUser.getStringCalendarList();
            
            vectorEventGroup = new Vector();
            vectorEventGroup = sampleUser.getVectorEGList(0);
            
            vectorContacts = new Vector();
            vectorContacts = sampleUser.getVectorContactList();//database.getDBContactVector(currentUserID);//sampleUser.getVectorContactList();
            
            //Contact Tab
            tabAddSelected = false;
            tabDeleteSelected = false;
            tabEditSelected = false;
            
            //**********************************************************************
            //*
            //*     Main Layout Panel - (BorderLayout)
            //* 
            //**********************************************************************
            //pnlLogin.setVisible(false);
            pnlMain = new JPanel(null);
            pnlMain.setVisible(true);
            //**********************************************************************
            //*
            //*     North Main Panel
            //*        Left   - Username TBD
            //*        Center - 
            //*        Right  - Settings TBD
            //*
            //**********************************************************************
            pnlNorthMain = new JPanel();
            //pnlNorthMain.setBackground(Color.BLUE);

            //**********************************************************************
            //*
            //*     South Main Panel
            //*             -
            //* 
            //**********************************************************************
            pnlSouthMain = new JPanel();
            btnLogin.addActionListener(GUI.this);

            //**********************************************************************
            //*
            //*     East Main Panel
            //*        Upper - Agenda
            //*        Lower - Property Panel
            //* 
            //**********************************************************************
            pnlEastMain = new JPanel(new GridLayout(2, 1, 0, 0));
            pnlEastMain.setBounds(800, 0, 200, 600);
            
            //**********************************************************************
            //East Upper Panel - Agenda
            //**********************************************************************
            pnlEastUpperMain = new JPanel(new BorderLayout());
            pnlEastUpperMain.setBorder(BorderFactory.createLineBorder(Color.black));

            JLabel lblEastUpperTitle = new JLabel("Agenda");
            btnLogout = new JButton("Save All");
            btnLogout.addActionListener(this);
            JPanel pnlLogout = new JPanel(new GridLayout(1,2));
            lblEastUpperTitle.setHorizontalAlignment(JLabel.CENTER);

            //**********************************************************************
            //Agenda List
            pnlAgenda = new JPanel();
            pnlAgenda.setBorder(BorderFactory.createLoweredBevelBorder());

            ReloadAgenda();

            //**********************************************************************
            //Agenda Add Panels
            scrollAgenda = new JScrollPane(pnlAgenda);
            
            pnlLogout.add(lblEastUpperTitle);
            pnlLogout.add(btnLogout);
            
            pnlEastUpperMain.add(pnlLogout, BorderLayout.NORTH);
            pnlEastUpperMain.add(scrollAgenda, BorderLayout.CENTER);

            //**********************************************************************
            //East Lower Panel - Property Panel
            //**********************************************************************
            pnlEastLowerMain = new JPanel(new BorderLayout());

            JLabel lblEastLowerTitle = new JLabel("Properties");
            lblEastLowerTitle.setBorder(BorderFactory.createLineBorder(Color.black));
            lblEastLowerTitle.setHorizontalAlignment(JLabel.CENTER);
            
            //**********************************************************************
            //Properties - Appointment
            pnlAppointment = new JPanel(new GridLayout(4, 2, 0, 0));
            pnlAppointment.setBorder(BorderFactory.createLoweredBevelBorder());

            
            lblAppStart = new JLabel("Start:");
            txtAppStart = new JTextField(4);
            lblAppEnd = new JLabel("End:");
            txtAppEnd = new JTextField(4);
            lblAppDuration = new JLabel("Duration:");
            txtAppDuration = new JTextField(4);
            lblAppLocation = new JLabel("Location:");
            txtAppLocation = new JTextField(4);
            // Attendees list 

            pnlAppointment.add(lblAppStart);
            pnlAppointment.add(txtAppStart);
            pnlAppointment.add(lblAppEnd);
            pnlAppointment.add(txtAppEnd);
            pnlAppointment.add(lblAppDuration);
            pnlAppointment.add(txtAppDuration);
            pnlAppointment.add(lblAppLocation);
            pnlAppointment.add(txtAppLocation);
            //add AttendeesList

            //**********************************************************************
            //Properties - DayDescriptors
            pnlDD = new JPanel(new GridLayout(3, 2, 0, 0));
            pnlDD.setBorder(BorderFactory.createLoweredBevelBorder());

            
            lblDDTitle = new JLabel("Title:");
            txtDDTitle = new JTextField();
            lblDDStart = new JLabel("Start:");
            txtDDStart = new JTextField();
            lblDDEnd = new JLabel("End:");
            txtDDEnd = new JTextField();

            pnlDD.add(lblDDTitle);
            pnlDD.add(txtDDTitle);
            pnlDD.add(lblDDStart);
            pnlDD.add(txtDDStart);
            pnlDD.add(lblDDEnd);
            pnlDD.add(txtDDEnd);

            //**********************************************************************
            //Properties - Deadline
            pnlDeadline = new JPanel(new GridLayout(5, 2, 0, 0));
            pnlDeadline.setBorder(BorderFactory.createLoweredBevelBorder());

            
            lblDeadlineType = new JLabel("Type:");
            txtDeadlineType = new JTextField();
            lblDeadlineStart = new JLabel("Start:");
            txtDeadlineStart = new JTextField();
            lblDeadlineEnd = new JLabel("End:");
            txtDeadlineEnd = new JTextField();
            lblDeadlinePerComp = new JLabel("Percent Complete:");
            txtDeadlinePerComp = new JTextField();
            lblDeadlineHrsWork = new JLabel("Hours of Work:");
            txtDeadlineHrsWork = new JTextField();

            pnlDeadline.add(lblDeadlineType);
            pnlDeadline.add(txtDeadlineType);
            pnlDeadline.add(lblDeadlineStart);
            pnlDeadline.add(txtDeadlineStart);
            pnlDeadline.add(lblDeadlineEnd);
            pnlDeadline.add(txtDeadlineEnd);
            pnlDeadline.add(lblDeadlinePerComp);
            pnlDeadline.add(txtDeadlinePerComp);
            pnlDeadline.add(lblDeadlineHrsWork);
            pnlDeadline.add(txtDeadlineHrsWork);

            //**********************************************************************
            //Properties - Flight
            pnlFlight = new JPanel(new GridLayout(5, 2, 0, 0));
            pnlFlight.setBorder(BorderFactory.createLoweredBevelBorder());

            
            lblFlightDeparture = new JLabel("Departure:");
            txtFlightDeparture = new JTextField();
            lblFlightReturn = new JLabel("Return:");
            txtFlightReturn = new JTextField();
            lblFlightConfirmCode = new JLabel("Confirmation Code:");
            txtFlightConfirmCode = new JTextField();
            lblFlightCheckinReminder = new JLabel("Check-in Reminder:");
            txtFlightCheckinReminder = new JTextField();
            lblFlightBoardingReminder = new JLabel("Boarding Reminder:");
            txtFlightBoardingReminder = new JTextField();

            pnlFlight.add(lblFlightDeparture);
            pnlFlight.add(txtFlightDeparture);
            pnlFlight.add(lblFlightReturn);
            pnlFlight.add(txtFlightReturn);
            pnlFlight.add(lblFlightConfirmCode);
            pnlFlight.add(txtFlightConfirmCode);
            pnlFlight.add(lblFlightCheckinReminder);
            pnlFlight.add(txtFlightCheckinReminder);
            pnlFlight.add(lblFlightBoardingReminder);
            pnlFlight.add(txtFlightBoardingReminder);

            //**********************************************************************
            //Properties - Meeting 
            pnlMeeting = new JPanel(new GridLayout(7, 2, 0, 0));
            pnlMeeting.setBorder(BorderFactory.createLoweredBevelBorder());

            
            lblMeetingStart = new JLabel("Start:");
            txtMeetingStart = new JTextField();
            lblMeetingEnd = new JLabel("End:");
            txtMeetingEnd = new JTextField();
            lblMeetingDuration = new JLabel("Duration:");
            txtMeetingDuration = new JTextField();
            lblMeetingType = new JLabel("Start");
            txtMeetingType = new JTextField();
            lblMeetingPhoneNum = new JLabel("Phone Number:");
            txtMeetingPhoneNum = new JTextField();
            lblMeetingPassword = new JLabel("Password:");
            txtMeetingPassword = new JTextField();
            lblMeetingAddress = new JLabel("Address:");       //String not class
            txtMeetingAddress = new JTextField();

            pnlMeeting.add(lblMeetingStart);
            pnlMeeting.add(txtMeetingStart);
            pnlMeeting.add(lblMeetingEnd);
            pnlMeeting.add(txtMeetingEnd);
            pnlMeeting.add(lblMeetingDuration);
            pnlMeeting.add(txtMeetingDuration);
            pnlMeeting.add(lblMeetingType);
            pnlMeeting.add(txtMeetingType);
            pnlMeeting.add(lblMeetingPhoneNum);
            pnlMeeting.add(txtMeetingPhoneNum);
            pnlMeeting.add(lblMeetingPassword);
            pnlMeeting.add(txtMeetingPassword);
            pnlMeeting.add(lblMeetingAddress);
            pnlMeeting.add(txtMeetingAddress);

            //**********************************************************************
            //Properties - Quick
            pnlQuick = new JPanel();
            pnlQuick.setBorder(BorderFactory.createLoweredBevelBorder());
            pnlQuick.setLayout(new BoxLayout(pnlQuick, BoxLayout.Y_AXIS));
            
            cbxQuickCalendar = new JComboBox(vectorCalendar);
            cbxQuickCalendar.setBorder(BorderFactory.createTitledBorder("Calendar:"));
            cbxQuickCalendar.setEditable(false);
            cbxQuickCalendar.addActionListener(this);
            
            JPanel pnlQuickEGList = new JPanel(new GridLayout(1,1));
            jlQuickEventGroup = new JList(vectorEventGroup);
            jlQuickEventGroup.setBorder(BorderFactory.createTitledBorder("Event Group:"));
            jlQuickEventGroup.setBackground(pnlMain.getBackground());
            pnlQuickEGList.add(jlQuickEventGroup);
            
            JPanel startDatePanel = new JPanel(new GridLayout(3, 1));
            
            Border blackline = BorderFactory.createLineBorder(Color.black);
            startDatePanel.setBorder(BorderFactory.createTitledBorder(blackline, "Start Date"));
            cbStartMonth = new JComboBox(MONTHS);
            cbStartMonth.setBorder(BorderFactory.createTitledBorder("Month"));
            cbStartDay = new JComboBox(DAYS_OF_MONTH);
            cbStartDay.setBorder(BorderFactory.createTitledBorder("Day"));
            cbStartYear = new JComboBox(YEARS);
            cbStartYear.setBorder(BorderFactory.createTitledBorder("Year"));
            cbStartHour = new JComboBox(HOURS);
            cbStartHour.setBorder(BorderFactory.createTitledBorder("Hour"));
            cbStartMinute = new JComboBox(MINUTES);
            cbStartMinute.setBorder(BorderFactory.createTitledBorder("Minute"));
            startDatePanel.add(cbStartMonth);
            startDatePanel.add(cbStartDay);
            startDatePanel.add(cbStartYear);
            
            JPanel startTimePanel = new JPanel(new GridLayout(1, 2));
            startTimePanel.setBorder(BorderFactory.createTitledBorder("Time"));
            startTimePanel.add(cbStartHour);
            startTimePanel.add(cbStartMinute);
            
            JPanel endDatePanel = new JPanel(new GridLayout(3, 1));
            endDatePanel.setBorder(BorderFactory.createTitledBorder(blackline,"End Date"));
            cbEndMonth = new JComboBox(MONTHS);
            cbEndMonth.setBorder(BorderFactory.createTitledBorder("Month"));
            cbEndDay = new JComboBox(DAYS_OF_MONTH);
            cbEndDay.setBorder(BorderFactory.createTitledBorder("Day"));
            cbEndYear = new JComboBox(YEARS);
            cbEndYear.setBorder(BorderFactory.createTitledBorder("Year"));
            endDatePanel.add(cbEndMonth);
            endDatePanel.add(cbEndDay);
            endDatePanel.add(cbEndYear);
            
            JPanel endTimePanel = new JPanel(new GridLayout(1, 2));
            endTimePanel.setBorder(BorderFactory.createTitledBorder("Time"));
            cbEndHour = new JComboBox(HOURS);
            cbEndMinute = new JComboBox(MINUTES);
            endTimePanel.add(cbEndHour);
            endTimePanel.add(cbEndMinute);
            
            txtQuickLocation = new JTextField();
            txtQuickLocation.setBorder(BorderFactory.createTitledBorder("Location"));
            txtQuickLocation.setBackground(pnlMain.getBackground());
            
            JPanel pnlQuickBtns = new JPanel(new GridLayout(1,2));
            quickSaveBtn = new JButton("Save");
            quickSaveBtn.addActionListener(this);
            quickCancelBtn = new JButton("Cancel");
            quickCancelBtn.addActionListener(this);
            pnlQuickBtns.add(quickSaveBtn);
            pnlQuickBtns.add(quickCancelBtn);
            
            txtQuickNote = new JTextField();
            txtQuickNote.setBorder(BorderFactory.createTitledBorder("Note"));
            txtQuickNote.setBackground(pnlMain.getBackground());
            txtQuickTitle = new JTextField();
            txtQuickTitle.setBorder(BorderFactory.createTitledBorder("Title"));
            txtQuickTitle.setBackground(pnlMain.getBackground());
            
            pnlQuick.add(cbxQuickCalendar);
            pnlQuick.add(pnlQuickEGList);
            pnlQuick.add(txtQuickTitle);
            pnlQuick.add(startDatePanel);
            pnlQuick.add(startTimePanel);
            pnlQuick.add(endDatePanel);
            pnlQuick.add(endTimePanel);
            pnlQuick.add(txtQuickLocation);
            pnlQuick.add(txtQuickNote);
            pnlQuick.add(pnlQuickBtns);

            //**********************************************************************
            //Properties - SchoolClass
            pnlSchoolClass = new JPanel(new GridLayout(5, 2, 0, 0));
            pnlSchoolClass.setBorder(BorderFactory.createLoweredBevelBorder());

            
            lblSchoolClassStart = new JLabel("Start:");
            txtSchoolClassStart = new JTextField();
            lblSchoolClassEnd = new JLabel("End:");
            txtSchoolClassEnd = new JTextField();
            lblSchoolClassDuration = new JLabel("Duration:");
            txtSchoolClassDuration = new JTextField();
            lblSchoolClassBuilding = new JLabel("Building:");
            txtSchoolClassBuilding = new JTextField();
            lblSchoolClassRoomNum = new JLabel("Room Number:");
            txtSchoolClassRoomNum = new JTextField();
            //Attendees

            pnlSchoolClass.add(lblSchoolClassStart);
            pnlSchoolClass.add(txtSchoolClassStart);
            pnlSchoolClass.add(lblSchoolClassEnd);
            pnlSchoolClass.add(txtSchoolClassEnd);
            pnlSchoolClass.add(lblSchoolClassDuration);
            pnlSchoolClass.add(txtSchoolClassDuration);
            pnlSchoolClass.add(lblSchoolClassBuilding);
            pnlSchoolClass.add(txtSchoolClassBuilding);
            pnlSchoolClass.add(lblSchoolClassRoomNum);
            pnlSchoolClass.add(txtSchoolClassRoomNum);
            //attendees

            //**********************************************************************
            //Properties - Vacation
            pnlVacation = new JPanel(new GridLayout(4, 2, 0, 0));
            pnlVacation.setBorder(BorderFactory.createLoweredBevelBorder());

            
            lblVacationStart = new JLabel("Start:");
            txtVacationStart = new JTextField();
            lblVacationEnd = new JLabel("End:");
            txtVacationEnd = new JTextField();
            lblVacationDuration = new JLabel("Duration:");
            txtVacationDuration = new JTextField();
            lblVacationLocation = new JLabel("Location:");
            txtVacationLocation = new JTextField();
            //Attendees

            pnlVacation.add(lblVacationStart);
            pnlVacation.add(txtVacationStart);
            pnlVacation.add(lblVacationEnd);
            pnlVacation.add(txtVacationEnd);
            pnlVacation.add(lblVacationDuration);
            pnlVacation.add(txtVacationDuration);
            pnlVacation.add(lblVacationLocation);
            pnlVacation.add(txtVacationLocation);
            //Attendees

            //**********************************************************************
            //Properties - Default
            pnlPropertiesDefault = new JPanel(new GridLayout(1,2,0,0));
            pnlPropertiesDefault.setBorder(BorderFactory.createLoweredBevelBorder());

            lblDefault = new JLabel("[Editor Window]");
            lblDefault.setHorizontalAlignment(JLabel.CENTER);
            
            pnlPropertiesDefault.add(lblDefault);

            //**********************************************************************        
            //Properties New Calendar Panel
            pnlNewCalendar = new JPanel();
            pnlNewCalendar.setBorder(BorderFactory.createLoweredBevelBorder());
            pnlNewCalendar.setLayout(new BoxLayout(pnlNewCalendar, BoxLayout.Y_AXIS));
            
            JPanel pnlNewCalendarName = new JPanel(new FlowLayout());
            pnlNewCalendarName.setBorder(BorderFactory.createTitledBorder("Calendar:"));
            JPanel pnlNewCalendarBtns = new JPanel();
            pnlNewCalendarBtns.setLayout(new BoxLayout(pnlNewCalendarBtns, BoxLayout.X_AXIS));
            
            lblNewCalendarName = new JLabel("Name:");
            txtNewCalendarName = new JTextField(10);
            btnNewCalendarSave = new JButton("Save");
            btnNewCalendarCancel = new JButton("Cancel");
            
            btnNewCalendarSave.addActionListener(this);
            btnNewCalendarCancel.addActionListener(this);
            
            pnlNewCalendarName.add(lblNewCalendarName);
            pnlNewCalendarName.add(txtNewCalendarName);
            pnlNewCalendarBtns.add(btnNewCalendarSave);
            pnlNewCalendarBtns.add(btnNewCalendarCancel);

            pnlNewCalendar.add(pnlNewCalendarName);
            pnlNewCalendar.add(pnlNewCalendarBtns);

            //**********************************************************************
            //Properties Delete Calendar Panel
            pnlDeleteCalendar = new JPanel(new BorderLayout());
            pnlDeleteCalendar.setBorder(BorderFactory.createLoweredBevelBorder());

            cbCalendarList = new JComboBox(vectorCalendar);
            cbCalendarList.setBorder(BorderFactory.createTitledBorder("Calendar:"));
            
            btnDeleteCalendar = new JButton("Delete");
            btnCancelCalendar = new JButton("Cancel");
            
            btnDeleteCalendar.addActionListener(this);
            btnCancelCalendar.addActionListener(this);
            
            JPanel pnlDeleteCalendarCenter = new JPanel(new FlowLayout());
            pnlDeleteCalendarCenter.add(btnDeleteCalendar);
            pnlDeleteCalendarCenter.add(btnCancelCalendar);

            pnlDeleteCalendar.add(cbCalendarList, BorderLayout.NORTH);
            pnlDeleteCalendar.add(pnlDeleteCalendarCenter, BorderLayout.SOUTH);
            
            //**********************************************************************
            //Properties New Event Group Panel
            pnlAddEventGroup = new JPanel(new BorderLayout());
            pnlAddEventGroup.setBorder(BorderFactory.createLoweredBevelBorder());

            cbCalendarListAddEG = new JComboBox(vectorCalendar);
            cbCalendarListAddEG.setBorder(BorderFactory.createTitledBorder("Calendar:"));
            
            lblNewEGName = new JLabel("Name:");
            txtNewEGName = new JTextField(10);
            btnNewEGSave = new JButton("Save");
            btnNewEGCancel = new JButton("Cancel");
            
            btnNewEGSave.addActionListener(this);
            btnNewEGCancel.addActionListener(this);
            
            JPanel pnlAddEventGroupCenter = new JPanel(new FlowLayout());
            pnlAddEventGroupCenter.setBorder(BorderFactory.createTitledBorder("Event Group:"));
            JPanel pnlAddEventGroupBtns = new JPanel(new FlowLayout());
            
            pnlAddEventGroupCenter.add(lblNewEGName);
            pnlAddEventGroupCenter.add(txtNewEGName);
            pnlAddEventGroupBtns.add(btnNewEGSave);
            pnlAddEventGroupBtns.add(btnNewEGCancel);
            
            pnlAddEventGroup.add(cbCalendarListAddEG, BorderLayout.NORTH);
            pnlAddEventGroup.add(pnlAddEventGroupCenter, BorderLayout.CENTER);
            pnlAddEventGroup.add(pnlAddEventGroupBtns, BorderLayout.SOUTH);
            
            //**********************************************************************
            //Properties Delete Event Group
            pnlDeleteEventGroup = new JPanel(new BorderLayout());
            pnlDeleteEventGroup.setBorder(BorderFactory.createLoweredBevelBorder());
  
            JPanel pnlDeleteEventGroupCenter = new JPanel(new GridLayout(2,1));
            cbDeleteCalendarList = new JComboBox(vectorCalendar);
            cbDeleteCalendarList.setBorder(BorderFactory.createTitledBorder("Calendar:"));
            cbEventGroupList = new JList(vectorEventGroup);
            cbEventGroupList.setBorder(BorderFactory.createTitledBorder("Event Group:"));
            cbEventGroupList.setBackground(pnlDeleteEventGroup.getBackground());

            JPanel pnlDeleteEventGroupBtns = new JPanel(new FlowLayout());
            btnDeleteEG = new JButton("Delete");
            btnCancelEG = new JButton("Cancel");
            
            btnDeleteEG.addActionListener(this);
            btnCancelEG.addActionListener(this);
            cbDeleteCalendarList.addActionListener(this);
            
            pnlDeleteEventGroupCenter.add(cbDeleteCalendarList);
            pnlDeleteEventGroupCenter.add(cbEventGroupList);
            pnlDeleteEventGroupBtns.add(btnDeleteEG);
            pnlDeleteEventGroupBtns.add(btnCancelEG);
            
            pnlDeleteEventGroup.add(pnlDeleteEventGroupCenter, BorderLayout.CENTER);
            pnlDeleteEventGroup.add(pnlDeleteEventGroupBtns, BorderLayout.SOUTH);
            
            //**********************************************************************
            //Properties - Delete Contact Panel
            pnlDeleteContactPanel = new JPanel(new BorderLayout());
            //pnlDeleteContactPanel.setLayout(new BoxLayout(pnlDeleteContactPanel, BoxLayout.Y_AXIS));
            pnlDeleteContactPanel.setBorder(BorderFactory.createLoweredBevelBorder());
           
            cbxDeleteContactList = new JComboBox(vectorContacts);
            
            JPanel pnlDeleteContactBtns = new JPanel(new GridLayout(1,2));
            btnDeleteDeleteContact = new JButton("Delete");
            btnDeleteCancelContact = new JButton("Cancel");
            
            btnDeleteDeleteContact.addActionListener(this);
            btnDeleteCancelContact.addActionListener(this);
            
            pnlDeleteContactBtns.add(btnDeleteDeleteContact);
            pnlDeleteContactBtns.add(btnDeleteCancelContact);
            
            pnlDeleteContactPanel.add(cbxDeleteContactList, BorderLayout.NORTH);
            pnlDeleteContactPanel.add(pnlDeleteContactBtns, BorderLayout.SOUTH);
            
            //**********************************************************************
            //Properties - Edit Contact Panel
            pnlEditContactPanel = new JPanel();
            pnlEditContactPanel.setBorder(BorderFactory.createLoweredBevelBorder());

            
            pnlFrmMainContact = new JPanel(new BorderLayout());

            JPanel pnlFrmCenter = new JPanel();
            pnlFrmCenter.setLayout(new BoxLayout(pnlFrmCenter, BoxLayout.Y_AXIS));
            
            pnlFrmUpperContact = new JPanel();
            pnlFrmUpperContact.setLayout(new BoxLayout(pnlFrmUpperContact, BoxLayout.Y_AXIS));
            
            pnlFrmLowerContact = new JPanel();
            pnlFrmLowerContact.setLayout(new BoxLayout(pnlFrmLowerContact, BoxLayout.Y_AXIS));
            
            pnlEditContact = new JPanel(new GridLayout(11,2,0,0));
            
            txtPrefix = new JTextField(4);
            txtPrefix.setBorder(BorderFactory.createTitledBorder("Prefix:"));
            txtPrefix.setBackground(pnlMain.getBackground());
            txtFirstName = new JTextField(4);
            txtFirstName.setBorder(BorderFactory.createTitledBorder("First Name:"));
            txtFirstName.setBackground(pnlMain.getBackground());
            txtNickName = new JTextField(4);
            txtNickName.setBorder(BorderFactory.createTitledBorder("Nickname:"));
            txtNickName.setBackground(pnlMain.getBackground());
            txtMiddleName = new JTextField(4);
            txtMiddleName.setBorder(BorderFactory.createTitledBorder("Middle Name:"));
            txtMiddleName.setBackground(pnlMain.getBackground());
            txtLastName = new JTextField(4);
            txtLastName.setBorder(BorderFactory.createTitledBorder("Last Name:"));
            txtLastName.setBackground(pnlMain.getBackground());
            txtSuffix = new JTextField(4);
            txtSuffix.setBorder(BorderFactory.createTitledBorder("Suffix"));
            txtSuffix.setBackground(pnlMain.getBackground());

            txtJobTitle = new JTextField(4);
            txtJobTitle.setBorder(BorderFactory.createTitledBorder("Job Title:"));
            txtJobTitle.setBackground(pnlMain.getBackground());
            txtDepartment = new JTextField(4);
            txtDepartment.setBorder(BorderFactory.createTitledBorder("Department:"));
            txtDepartment.setBackground(pnlMain.getBackground());
            txtCompany = new JTextField(4);
            txtCompany.setBorder(BorderFactory.createTitledBorder("Company:"));
            txtCompany.setBackground(pnlMain.getBackground());

            txtBirthDate = new JTextField();
            txtBirthDate.setBorder(BorderFactory.createTitledBorder("Birthdate:"));
            txtBirthDate.setBackground(pnlMain.getBackground());
            
            lblPhoneNumbers = new JLabel();
            lblAddress = new JLabel();
            lblEmails = new JTextArea();
            lblSocialMedia = new JLabel();
            lblCustomList =  new JLabel();

            pnlEditContact.add(txtPrefix);
            pnlEditContact.add(txtFirstName);
            pnlEditContact.add(txtNickName);
            pnlEditContact.add(txtMiddleName);
            pnlEditContact.add(txtLastName);
            pnlEditContact.add(txtSuffix);
            pnlEditContact.add(txtBirthDate);

            pnlEditContact.add(txtJobTitle);
            pnlEditContact.add(txtDepartment);
            pnlEditContact.add(txtCompany);   
            
            cbxContactInfoType = new JComboBox(new String[]{"Email","Phone","Address","Social","Custom"});
            cbxContactInfoType.setBorder(BorderFactory.createTitledBorder("Select Category:"));
            pnlEditContact.add(cbxContactInfoType);
            cbxContactInfoType.addItemListener(this);
            
            vectorListofTitleofType = new Vector(sampleUser.getVectorContactAddress(0));
            vectorAddressTitles = new Vector(sampleUser.getVectorContactAddress(0));
            vectorPhoneTitles = new Vector(sampleUser.getVectorContactPhoneNum(0));
            vectorEmailTitles = new Vector(sampleUser.getVectorContactEmail(0));
            vectorSocialTitles = new Vector(sampleUser.getVectorContactSM(0));
            vectorCustomTitles = new Vector(sampleUser.getVectorContactCustom(0));
            
            paneContactTabs = new JTabbedPane();
            
            pnlAdd = new JPanel();
            pnlAdd.setLayout(new BoxLayout(pnlAdd, BoxLayout.Y_AXIS));
            
            pnlDelete = new JPanel();
            pnlDelete.setLayout(new BoxLayout(pnlDelete, BoxLayout.Y_AXIS));
            //cmbDeleteTitlesOfType = new JComboBox(vectorListofTitleofType);
            //pnlDelete.add(cmbDeleteTitlesOfType);
            
            pnlEdit = new JPanel();
            pnlEdit.setLayout(new BoxLayout(pnlEdit, BoxLayout.Y_AXIS));
            //cmbEditTitlesOfType = new JComboBox(vectorListofTitleofType);
            
            jlEditPhoneTitles = new JList(vectorPhoneTitles);
            jlEditPhoneTitles.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            jlDeletePhoneTitles = new JList(vectorPhoneTitles);
            jlDeletePhoneTitles.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            
            cmbEditTitlesPhone = new JComboBox(vectorPhoneTitles);
            //cmbEditTitlesPhone.addItemListener(this);
            cmbDeleteTitlesPhone = new JComboBox(vectorPhoneTitles);
            //cmbDeleteTitlesPhone.addItemListener(this);
            
            jlEditTitlesOfType = new JList(vectorAddressTitles);
            //pnlEdit.add(jlEditTitlesOfType);
            
            pnlAddress = new JPanel();
            pnlAddress.setLayout(new BoxLayout(pnlAddress, BoxLayout.Y_AXIS));
            txtAddressLabel = new JTextField(4);
            txtAddressLabel.setBorder(BorderFactory.createTitledBorder("Title:"));
            txtAddressLabel.setBackground(pnlMain.getBackground());
            txtStreetNum = new JTextField(4);
            txtStreetNum.setBorder(BorderFactory.createTitledBorder("Street Number:"));
            txtStreetNum.setBackground(pnlMain.getBackground());
            txtStreetName = new JTextField(4);
            txtStreetName.setBorder(BorderFactory.createTitledBorder("Street Name:"));
            txtStreetName.setBackground(pnlMain.getBackground());
            txtCity = new JTextField(4);
            txtCity.setBorder(BorderFactory.createTitledBorder("City:"));
            txtCity.setBackground(pnlMain.getBackground());
            txtState = new JTextField(4);
            txtState.setBorder(BorderFactory.createTitledBorder("State:"));
            txtState.setBackground(pnlMain.getBackground());
            txtZipcode = new JTextField(4);
            txtZipcode.setBorder(BorderFactory.createTitledBorder("Zipcode:"));
            txtZipcode.setBackground(pnlMain.getBackground());
            txtCountry = new JTextField(4);
            txtCountry.setBorder(BorderFactory.createTitledBorder("Country:"));
            txtCountry.setBackground(pnlMain.getBackground());
            btnSaveAddress = new JButton("Save");
            btnDeleteAddress = new JButton("Delete");
            
            btnSaveAddress.addActionListener(this);
            btnDeleteAddress.addActionListener(this);
            
            cmbEditTitlesAddress = new JComboBox(vectorAddressTitles);
            cmbDeleteTitlesAddress = new JComboBox(vectorAddressTitles);
            
            pnlAddress.add(txtAddressLabel);
            pnlAddress.add(txtStreetNum);
            pnlAddress.add(txtStreetName);
            pnlAddress.add(txtCity);
            pnlAddress.add(txtState);
            pnlAddress.add(txtZipcode);
            pnlAddress.add(txtCountry);
            pnlAddress.add(btnSaveAddress);        
           
            pnlPhoneNumber = new JPanel();
            pnlPhoneNumber.setLayout(new BoxLayout(pnlPhoneNumber, BoxLayout.Y_AXIS));
            txtPhoneLabel = new JTextField(4);
            txtPhoneLabel.setBorder(BorderFactory.createTitledBorder("Title:"));
            txtPhoneLabel.setBackground(pnlMain.getBackground());
            txtPhoneNumber = new JTextField(4);
            txtPhoneNumber.setBorder(BorderFactory.createTitledBorder("Phone Number:"));
            txtPhoneNumber.setBackground(pnlMain.getBackground());
            txtPhoneExtension = new JTextField(4);
            txtPhoneExtension.setBorder(BorderFactory.createTitledBorder("Phone Extension:"));
            txtPhoneExtension.setBackground(pnlMain.getBackground());
            btnSavePhone = new JButton("Add");
            //btnCancelPhone = new JButton("Cancel");
            btnDeletePhone = new JButton("Delete");
            
            btnSavePhone.addActionListener(this);
            //btnCancelPhone.addActionListener(this);
            btnDeletePhone.addActionListener(this);
            
            pnlPhoneNumber.add(txtPhoneLabel);
            pnlPhoneNumber.add(txtPhoneNumber);
            pnlPhoneNumber.add(txtPhoneExtension);  
            pnlPhoneNumber.add(btnSavePhone);  
            //pnlPhoneNumber.add(btnCancelPhone);  
             
            pnlEmail = new JPanel();
            pnlEmail.setLayout(new BoxLayout(pnlEmail, BoxLayout.Y_AXIS));
            txtEmailLabel = new JTextField(4);
            txtEmailLabel.setBorder(BorderFactory.createTitledBorder("Title:"));
            txtEmailLabel.setBackground(pnlMain.getBackground());
            txtEmail = new JTextField(4);
            txtEmail.setBorder(BorderFactory.createTitledBorder("Email:"));
            txtEmail.setBackground(pnlMain.getBackground());
            btnSaveEmail = new JButton("Save");
            btnDeleteEmail = new JButton("Delete");
            
            btnSaveEmail.addActionListener(this);
            btnDeleteEmail.addActionListener(this);
            
            cmbEditTitlesEmail = new JComboBox(vectorEmailTitles);
            cmbDeleteTitlesEmail = new JComboBox(vectorEmailTitles);
            
            pnlEmail.add(txtEmailLabel);
            pnlEmail.add(txtEmail);
            pnlEmail.add(btnSaveEmail);
            
            pnlSocialMedia = new JPanel();
            pnlSocialMedia.setLayout(new BoxLayout(pnlSocialMedia, BoxLayout.Y_AXIS));
            txtSMLabel = new JTextField(4);
            txtSMLabel.setBorder(BorderFactory.createTitledBorder("Title:"));
            txtSMLabel.setBackground(pnlMain.getBackground());
            txtSM = new JTextField(4);
            txtSM.setBorder(BorderFactory.createTitledBorder("Social Media:"));
            txtSM.setBackground(pnlMain.getBackground());
            btnSaveSM = new JButton("Save");
            btnDeleteSM = new JButton("Delete");
            
            cmbEditTitlesSM = new JComboBox(vectorSocialTitles);
            cmbDeleteTitlesSM = new JComboBox(vectorSocialTitles);
            
            btnSaveSM.addActionListener(this);
            btnDeleteSM.addActionListener(this);
            
            pnlSocialMedia.add(txtSMLabel);
            pnlSocialMedia.add(txtSM);
            pnlSocialMedia.add(btnSaveSM);
            
            pnlCustom = new JPanel();
            pnlCustom.setLayout(new BoxLayout(pnlCustom, BoxLayout.Y_AXIS));
            txtCustomLabel = new JTextField(4);
            txtCustomLabel.setBorder(BorderFactory.createTitledBorder("Title:"));
            txtCustomLabel.setBackground(pnlMain.getBackground());
            txtCustomBody = new JTextField(4);
            txtCustomBody.setBorder(BorderFactory.createTitledBorder("Body:"));
            txtCustomBody.setBackground(pnlMain.getBackground());
            btnSaveCustom = new JButton("Save");
            btnDeleteCustom = new JButton("Delete");
            cmbEditTitlesCustom = new JComboBox(vectorCustomTitles);
            cmbDeleteTitlesCustom = new JComboBox(vectorCustomTitles);
            
            btnSaveCustom.addActionListener(this);
            btnDeleteCustom.addActionListener(this);

            pnlCustom.add(txtCustomLabel);
            pnlCustom.add(txtCustomBody);
            pnlCustom.add(btnSaveCustom);
            
            pnlAdd.add(pnlAddress);
            pnlAdd.add(pnlPhoneNumber);
            pnlAdd.add(pnlEmail);
            pnlAdd.add(pnlSocialMedia);
            pnlAdd.add(pnlCustom);
            
            pnlEdit.add(pnlAddress);
            pnlEdit.add(pnlPhoneNumber);
            pnlEdit.add(pnlEmail);
            pnlEdit.add(pnlSocialMedia);
            pnlEdit.add(pnlCustom);
            
            pnlDelete.add(pnlAddress);
            pnlDelete.add(pnlPhoneNumber);
            pnlDelete.add(pnlEmail);
            pnlDelete.add(pnlSocialMedia);
            pnlDelete.add(pnlCustom);
            
            pnlAddress.setVisible(false);
            pnlPhoneNumber.setVisible(false);
            pnlEmail.setVisible(false);
            pnlSocialMedia.setVisible(false);
            pnlCustom.setVisible(false);
            
            paneContactTabs.addTab("Add", pnlAdd);
            paneContactTabs.addTab("Edit", pnlEdit);
            paneContactTabs.addTab("Delete", pnlDelete);
            
            //Event Handling for Contact Add, Delete, Edit Tabs in Properties Panel
            changeListener = new ChangeListener() {
                public void stateChanged(ChangeEvent changeEvent) {
                    JTabbedPane sourceTabbedPane = (JTabbedPane) changeEvent.getSource();
                    tabIndex = sourceTabbedPane.getSelectedIndex();
                    
                    if(tabIndex == 0)
                    {
                        //System.out.print("add");
                        tabAddSelected = true;
                        tabDeleteSelected= false;
                        tabEditSelected = false;
                        pnlAdd.removeAll();
                        pnlEdit.removeAll();
                        pnlDelete.removeAll();
                        if(cbxContactInfoType.getSelectedItem().equals("Address"))
                        {
                            btnSaveAddress.setText("Add");
                            txtAddressLabel.setText("");
                            txtStreetNum.setText("");
                            txtStreetName.setText("");
                            txtCity.setText("");
                            txtState.setText("");
                            txtZipcode.setText("");
                            txtCountry.setText("");
                            pnlAdd.add(pnlAddress);
                            pnlAddress.setVisible(true);
                        }
                        else if(cbxContactInfoType.getSelectedItem().equals("Phone"))
                        {
                            btnSavePhone.setText("Add");
                            txtPhoneLabel.setText("");
                            txtPhoneNumber.setText("");
                            txtPhoneExtension.setText("");
                            pnlAdd.add(pnlPhoneNumber);
                            pnlPhoneNumber.setVisible(true);
                        }
                        else if(cbxContactInfoType.getSelectedItem().equals("Email"))
                        {
                            btnSaveEmail.setText("Add");
                            txtEmailLabel.setText("");
                            txtEmail.setText("");
                            pnlAdd.add(pnlEmail);
                            pnlEmail.setVisible(true);
                        }
                        else if(cbxContactInfoType.getSelectedItem().equals("Social"))
                        {
                            btnSaveSM.setText("Add");
                            txtSMLabel.setText("");
                            txtSM.setText("");
                            pnlAdd.add(pnlSocialMedia);
                            pnlSocialMedia.setVisible(true);
                            
                        }
                        else if(cbxContactInfoType.getSelectedItem().equals("Custom"))
                        {
                            btnSaveCustom.setText("Add");
                            txtCustomLabel.setText("");
                            txtCustomBody.setText("");
                            pnlAdd.add(pnlCustom);
                            pnlCustom.setVisible(true);
                        }  
                    }
                    else if(tabIndex == 1)
                    {
                        tabAddSelected = false;
                        tabDeleteSelected= false;
                        tabEditSelected = true;
                        //System.out.print("edit");
                        pnlAdd.removeAll();
                        pnlEdit.removeAll();
                        pnlDelete.removeAll();
                        //pnlEdit.add(cmbEditTitlesOfType);
                        if(cbxContactInfoType.getSelectedItem().equals("Address"))
                        {
                            btnSaveAddress.setText("Save");
                            vectorAddressTitles.removeAllElements();
                            vectorAddressTitles.addAll(sampleUser.getVectorContactAddress(cbContactList.getSelectedIndex()));
                            cmbEditTitlesAddress = new JComboBox(vectorAddressTitles);
                            cmbEditTitlesAddress.addItemListener(GUI.this);
                            Address tempAdddress = sampleUser.getContactAddress(cbContactList.getSelectedIndex(),cmbEditTitlesAddress.getSelectedIndex());
                            txtAddressLabel.setText(tempAdddress.getAddressLabel());
                            txtStreetNum.setText(Double.toString(tempAdddress.getStreetNumber()));
                            txtStreetName.setText(tempAdddress.getStreetName());
                            txtCity.setText(tempAdddress.getCity());
                            txtState.setText(tempAdddress.getState());
                            txtZipcode.setText(Double.toString(tempAdddress.getZipcode()));
                            txtCountry.setText(tempAdddress.getCountry());
                            pnlEdit.add(cmbEditTitlesAddress);
                            pnlEdit.add(pnlAddress);
                            pnlAddress.setVisible(true);
                        }
                        else if(cbxContactInfoType.getSelectedItem().equals("Phone"))
                        {    
                            btnSavePhone.setText("Save");
                            vectorPhoneTitles.removeAllElements();
                            vectorPhoneTitles.addAll(sampleUser.getVectorContactPhoneNum(cbContactList.getSelectedIndex()));
                            cmbEditTitlesPhone = new JComboBox(vectorPhoneTitles);
                            cmbEditTitlesPhone.addItemListener(GUI.this);
                            PhoneNumber tempNum = sampleUser.getContactPhoneNumber(cbContactList.getSelectedIndex(),cmbEditTitlesPhone.getSelectedIndex());
                            txtPhoneLabel.setText(tempNum.getNumberLabel());
                            txtPhoneNumber.setText(tempNum.getNumber());
                            txtPhoneExtension.setText(Double.toString(tempNum.getExtension()));
                            pnlEdit.add(cmbEditTitlesPhone);
                            pnlEdit.add(pnlPhoneNumber);
                            pnlPhoneNumber.setVisible(true);
                        }
                        else if(cbxContactInfoType.getSelectedItem().equals("Email"))
                        {
                            btnSaveEmail.setText("Edit");
                            vectorEmailTitles.removeAllElements();
                            vectorEmailTitles.addAll(sampleUser.getVectorContactEmail(cbContactList.getSelectedIndex()));
                            cmbEditTitlesEmail = new JComboBox(vectorEmailTitles);
                            cmbEditTitlesEmail.addItemListener(GUI.this);
                            Email tempEmail = sampleUser.getContactEmail(cbContactList.getSelectedIndex(),cmbEditTitlesEmail.getSelectedIndex());
                            txtEmailLabel.setText(tempEmail.getAccountLabel());
                            txtEmail.setText(tempEmail.getEmail());
                            pnlEdit.add(cmbEditTitlesEmail);
                            pnlEdit.add(pnlEmail);
                            pnlEmail.setVisible(true);
                        }
                        else if(cbxContactInfoType.getSelectedItem().equals("Social"))
                        {
                            btnSaveSM.setText("Edit");
                            vectorSocialTitles.removeAllElements();
                            vectorSocialTitles.addAll(sampleUser.getVectorContactSM(cbContactList.getSelectedIndex()));
                            cmbEditTitlesSM = new JComboBox(vectorSocialTitles);
                            cmbEditTitlesSM.addItemListener(GUI.this);
                            SocialMedia tempSM = sampleUser.getContactSM(cbContactList.getSelectedIndex(),cmbEditTitlesSM.getSelectedIndex());
                            txtSMLabel.setText(tempSM.getSocialLabel());
                            txtSM.setText(tempSM.getUsername());
                            pnlEdit.add(cmbEditTitlesSM);
                            pnlEdit.add(pnlSocialMedia);
                            pnlSocialMedia.setVisible(true);
                        }
                        else if(cbxContactInfoType.getSelectedItem().equals("Custom"))
                        {
                            btnSaveCustom.setText("Edit");
                            vectorCustomTitles.removeAllElements();
                            vectorCustomTitles.addAll(sampleUser.getVectorContactCustom(cbContactList.getSelectedIndex()));
                            cmbEditTitlesCustom = new JComboBox(vectorCustomTitles);
                            cmbEditTitlesCustom.addItemListener(GUI.this);
                            CustomList tempCustom = sampleUser.getContactCustom(cbContactList.getSelectedIndex(),cmbEditTitlesCustom.getSelectedIndex());
                            txtCustomLabel.setText(tempCustom.getTitleDescriptor());
                            txtCustomBody.setText(tempCustom.getBody());
                            pnlEdit.add(cmbEditTitlesCustom);
                            pnlEdit.add(pnlCustom);
                            pnlCustom.setVisible(true);
                        }  
                    }
                    else if(tabIndex == 2)
                    {
                        tabAddSelected = false;
                        tabDeleteSelected= true;
                        tabEditSelected = false;
                        //System.out.print("delelte");
                        pnlAdd.removeAll();
                        pnlEdit.removeAll();
                        pnlDelete.removeAll();
                        //pnlDelete.add(cmbDeleteTitlesOfType);
                        if(cbxContactInfoType.getSelectedItem().equals("Address"))
                        {
                            vectorAddressTitles.removeAllElements();
                            vectorAddressTitles.addAll(sampleUser.getVectorContactAddress(cbContactList.getSelectedIndex()));
                            cmbDeleteTitlesAddress= new JComboBox(vectorAddressTitles);
                            cmbDeleteTitlesAddress.addItemListener(GUI.this);
                            pnlDelete.add(cmbDeleteTitlesAddress);
                            pnlDelete.add(btnDeleteAddress);
                            
                        }
                        else if(cbxContactInfoType.getSelectedItem().equals("Phone"))
                        {
                            vectorPhoneTitles.removeAllElements();
                            vectorPhoneTitles.addAll(sampleUser.getVectorContactPhoneNum(cbContactList.getSelectedIndex()));
                            cmbDeleteTitlesPhone = new JComboBox(vectorPhoneTitles);
                            cmbDeleteTitlesPhone.addItemListener(GUI.this);
                            pnlDelete.add(cmbDeleteTitlesPhone);
                            pnlDelete.add(btnDeletePhone);
                            
                        }
                        else if(cbxContactInfoType.getSelectedItem().equals("Email"))
                        {
                            vectorEmailTitles.removeAllElements();
                            vectorEmailTitles.addAll(sampleUser.getVectorContactEmail(cbContactList.getSelectedIndex()));
                            cmbDeleteTitlesEmail= new JComboBox(vectorEmailTitles);
                            cmbDeleteTitlesEmail.addItemListener(GUI.this);
                            pnlDelete.add(cmbDeleteTitlesEmail);
                            pnlDelete.add(btnDeleteEmail);
                        }
                        else if(cbxContactInfoType.getSelectedItem().equals("Social"))
                        {
                            vectorSocialTitles.removeAllElements();
                            vectorSocialTitles.addAll(sampleUser.getVectorContactSM(cbContactList.getSelectedIndex()));
                            cmbDeleteTitlesSM= new JComboBox(vectorSocialTitles);
                            cmbDeleteTitlesSM.addItemListener(GUI.this);
                            pnlDelete.add(cmbDeleteTitlesSM);
                            pnlDelete.add(btnDeleteSM);
                        }
                        else if(cbxContactInfoType.getSelectedItem().equals("Custom"))
                        {
                            vectorCustomTitles.removeAllElements();
                            vectorCustomTitles.addAll(sampleUser.getVectorContactCustom(cbContactList.getSelectedIndex()));
                            cmbDeleteTitlesCustom = new JComboBox(vectorCustomTitles);
                            cmbDeleteTitlesCustom.addItemListener(GUI.this);
                            pnlDelete.add(cmbDeleteTitlesCustom);
                            pnlDelete.add(btnDeleteCustom);      
                        }
                    } 
                }
            };
            
            paneContactTabs.addChangeListener(changeListener);
            
            JPanel pnlFrmSouth = new JPanel();
            btnSaveContact = new JButton("Save");
            btnCancelContact = new JButton("Cancel");
                   
            btnSaveContact.addActionListener(this);
            btnCancelContact.addActionListener(this);
            
            pnlFrmSouth.add(btnSaveContact);
            pnlFrmSouth.add(btnCancelContact);
            
            pnlFrmUpperContact.add(pnlEditContact);
            pnlFrmLowerContact.add(paneContactTabs);
            
            pnlFrmCenter.add(pnlFrmUpperContact);
            pnlFrmCenter.add(pnlFrmLowerContact);
            
            pnlFrmMainContact.add(pnlFrmCenter, BorderLayout.CENTER);
            pnlFrmMainContact.add(pnlFrmSouth, BorderLayout.NORTH); 
            
            pnlEditContactPanel.add(pnlFrmMainContact);
            
            //**********************************************************************        
            //Properties Set Visibility 
            pnlPropertiesDefault.setVisible(true);
            pnlAppointment.setVisible(false);
            pnlDD.setVisible(false);
            pnlDeadline.setVisible(false);
            pnlFlight.setVisible(false);
            pnlMeeting.setVisible(false);
            pnlQuick.setVisible(false);
            pnlSchoolClass.setVisible(false);
            pnlVacation.setVisible(false);
            pnlNewCalendar.setVisible(false);
            pnlDeleteCalendar.setVisible(false);
            pnlAddEventGroup.setVisible(false);
            pnlDeleteEventGroup.setVisible(false);
            pnlEditContactPanel.setVisible(false);
            pnlDeleteContactPanel.setVisible(false);

            //**********************************************************************        
            //Properties Add Panels to Lower East
            JPanel pnlPropertyPanel = new JPanel(new BorderLayout());  
            pnlProperties = new JPanel();
            pnlProperties.setLayout(new BoxLayout(pnlProperties,BoxLayout.Y_AXIS));
            //lblEventType.setHorizontalAlignment(JLabel.CENTER);
            //pnlProperties.add(lblEventType, pnlProperties);
            pnlProperties.add(pnlPropertiesDefault, pnlProperties);
            pnlProperties.add(pnlAppointment, pnlProperties);
            pnlProperties.add(pnlDD, pnlProperties);
            pnlProperties.add(pnlDeadline, pnlProperties);
            pnlProperties.add(pnlFlight, pnlProperties);
            pnlProperties.add(pnlMeeting, pnlProperties);
            pnlProperties.add(pnlQuick, pnlProperties);
            pnlProperties.add(pnlSchoolClass, pnlProperties);
            pnlProperties.add(pnlVacation, pnlProperties);
            pnlProperties.add(pnlNewCalendar, pnlProperties);
            pnlProperties.add(pnlDeleteCalendar, pnlProperties);
            pnlProperties.add(pnlAddEventGroup, pnlProperties);
            pnlProperties.add(pnlDeleteEventGroup, pnlProperties);
            pnlProperties.add(pnlEditContactPanel, pnlProperties);
            pnlProperties.add(pnlDeleteContactPanel, pnlProperties);
            pnlProperties.setMaximumSize( pnlProperties.getPreferredSize() );
            //pnlPropertyPanel.add(lblEventType, BorderLayout.NORTH);
            pnlPropertyPanel.add(pnlProperties, BorderLayout.CENTER);
            scrollProperties = new JScrollPane(pnlPropertyPanel);
            scrollProperties.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            pnlEastLowerMain.add(lblEastLowerTitle, BorderLayout.NORTH);
            pnlEastLowerMain.add(scrollProperties, BorderLayout.CENTER);

            //**********************************************************************
            //*
            //*     West Main Panel
            //*         Upper - Calendars and Event Groups List
            //*         Lower - Contacts
            //* 
            //**********************************************************************
            pnlWestMain = new JPanel(new GridLayout(2, 1, 0, 0));
            pnlWestMain.setBounds(0,0,200,600);
            //**********************************************************************
            //West Upper Panel - Calendar + EG List
            //**********************************************************************
            pnlWestUpperMain = new JPanel(new BorderLayout());
            //pnlWestUpperMain.setBorder(BorderFactory.createLineBorder(Color.black));
            JLabel lblWestUpperTitle = new JLabel("Calendars");
            lblWestUpperTitle.setHorizontalAlignment(JLabel.CENTER);
            pnlCalTree = new JPanel();
            pnlCalTree.setLayout(new BoxLayout(pnlCalTree, BoxLayout.Y_AXIS));
            pnlCalTree.setBorder(BorderFactory.createLoweredBevelBorder());
            ReloadCalendars();
            ReloadAgenda();        
            pnlWestUpperSouth = new JPanel(new GridLayout(1,2));
            pnlCalBtns = new JPanel(new GridLayout(1,2));
            TitledBorder titCalBtns = BorderFactory.createTitledBorder("Calendar");
            titCalBtns.setTitleJustification(TitledBorder.CENTER);
            pnlCalBtns.setBorder(titCalBtns);
            btnAddCal = new JButton("+");
            btnDeleteCal = new JButton("-");
            btnAddCal.addActionListener(this);
            btnDeleteCal.addActionListener(this);
            JPanel pnlEGBtns = new JPanel(new GridLayout(1,2));
            TitledBorder titEBbtns = BorderFactory.createTitledBorder("Event Group");
            titEBbtns.setTitleJustification(TitledBorder.CENTER);
            pnlEGBtns.setBorder(titEBbtns);
            btnAddEGroup = new JButton("+");
            btnDeleteEGroup = new JButton("-");
            btnAddEGroup.addActionListener(this);
            btnDeleteEGroup.addActionListener(this);
            pnlCalBtns.add(btnAddCal);
            pnlCalBtns.add(btnDeleteCal);
            pnlEGBtns.add(btnAddEGroup);
            pnlEGBtns.add(btnDeleteEGroup);
            scrollCalTree = new JScrollPane(pnlCalTree);
            pnlWestUpperSouth.add(pnlCalBtns, pnlWestUpperSouth);
            pnlWestUpperSouth.add(pnlEGBtns, pnlWestUpperSouth);
            pnlWestUpperMain.add(lblWestUpperTitle, BorderLayout.NORTH);
            pnlWestUpperMain.add(scrollCalTree, BorderLayout.CENTER);
            pnlWestUpperMain.add(pnlWestUpperSouth, BorderLayout.SOUTH);

            //**********************************************************************
            //West Lower Panel - Contacts
            //**********************************************************************
            pnlWestLowerMain = new JPanel(new BorderLayout());
            pnlWestLowerMain.setBorder(BorderFactory.createLineBorder(Color.black));
            JLabel lblWestLowerTitle = new JLabel("Contacts");
            lblWestLowerTitle.setHorizontalAlignment(JLabel.CENTER);
            pnlContact = new JPanel(new BorderLayout());
            cbContactList = new JComboBox(vectorContacts);
            cbContactList.setEditable(false);
            cbContactList.addItemListener(this);
            pnlContactInfo = new JPanel();
            pnlContactInfo.setBorder(BorderFactory.createLoweredBevelBorder());
            pnlContactInfo.setLayout(new BoxLayout(pnlContactInfo, BoxLayout.Y_AXIS));
            curContact = new Contacts();
            curContact = sampleUser.getSelectContact(cbContactList.getSelectedIndex());
            lblName = new JLabel(curContact.getPrefix()+" "+curContact.getFirstName()+" ("+curContact.getNickName()+") "+curContact.getMiddleName()+" "+curContact.getLastName()+" "+curContact.getSuffix());
            JLabel lblBlank11 = new JLabel("   ");
            lblJobTitle = new JLabel(curContact.getJobTitle());
            lblDepartment = new JLabel(curContact.getDepartment());
            lblCompany = new JLabel(curContact.getCompany()); 
            lblAddress = new JLabel(curContact.toStringAddress());
            lblPhoneNumbers = new JLabel(curContact.toStringPhoneNumList());
            lblEmails = new JTextArea(curContact.toStringEmails());
            lblEmails.setEditable(false);
            lblEmails.setBackground(pnlMain.getBackground());
            LoadContactData(); // Method to load data from sample user
            pnlContactInfo.add(lblEmails, pnlContactInfo);      
            scrollContactInfo = new JScrollPane(pnlContactInfo);
            pnlContact.add(cbContactList, BorderLayout.NORTH);
            pnlContact.add(scrollContactInfo, BorderLayout.CENTER);
            pnlContactBtns = new JPanel(new GridLayout(1, 3, 0, 0));
            btnAddContact = new JButton("+");
            btnEditContact =  new JButton("Edit");
            btnRemoveContact = new JButton("-");
            btnAddContact.addActionListener(this);
            btnEditContact.addActionListener(this);
            btnRemoveContact.addActionListener(this);
            //**********************************************************************
            //Contacts Add Panels
            pnlContactBtns.add(btnAddContact);
            pnlContactBtns.add(btnRemoveContact);
            pnlContactBtns.add(btnEditContact);
            pnlWestLowerMain.add(lblWestLowerTitle, BorderLayout.NORTH);
            pnlWestLowerMain.add(pnlContact, BorderLayout.CENTER);
            pnlWestLowerMain.add(pnlContactBtns, BorderLayout.SOUTH);
            //**********************************************************************
            //*
            //*     Center Main Panel (BorderLayout)
            //*         Header  - [Left - Add,Today][Center - Clock][Right - Views]
            //*         Body - Day, Week, Multi-Week, Month, Year Panels
            //*         Footer  - [ ]
            //*          
            //*
            //**********************************************************************
            pnlCenterMain = new JPanel(new BorderLayout());
            pnlCenterMain.setBounds(200, 0, 600, 600);
            
            //**********************************************************************
            //Center North Panel
            //**********************************************************************
            pnlCenterNorth = new JPanel(new GridLayout(1, 3, 0, 0));

            //**********************************************************************
            //Center North Left - Add and Today Buttons
            pnlCenterNorthLeft = new JPanel(new GridLayout(1, 1, 0, 0));
            btnAddEvent = new JButton("Add Event");
            btnToday = new JButton("Today");
            btnAddEvent.addActionListener(this);
            btnToday.addActionListener(this);
            pnlCenterNorthLeft.add(btnAddEvent);
            pnlCenterNorthLeft.add(btnToday);

            //**********************************************************************
            //Center North Middle - Clock
            pnlCenterNorthMiddle = new JPanel(new GridLayout(1, 1, 0, 0));
            pnlCenterNorthMiddle.setBorder(BorderFactory.createLineBorder(Color.black));
            lblTodaysDate = new JLabel();
            lblTodaysDate.setText(stringTodaysDate);
            lblTodaysDate.setHorizontalAlignment(JLabel.CENTER);
            pnlCenterNorthMiddle.add(lblTodaysDate);

            //**********************************************************************
            //Center North Right - View Buttons
            pnlCenterNorthRight = new JPanel(new GridLayout(1, 5, 0, 0));
            Font fntViewBtns = new Font(lblEastLowerTitle.getName(), Font.PLAIN, 8);
            btnDay = new JButton("D");
            btnDay.setFont(fntViewBtns);
            btnWeek = new JButton("W");
            fntViewBtns = new Font(lblEastLowerTitle.getName(), Font.PLAIN, 6);
            btnWeek.setFont(fntViewBtns);
            btnMultiWeek = new JButton("MW");
            fntViewBtns = new Font(lblEastLowerTitle.getName(), Font.PLAIN, 5);
            btnMultiWeek.setFont(fntViewBtns);
            btnMonth = new JButton("M");
            fntViewBtns = new Font(lblEastLowerTitle.getName(), Font.PLAIN, 6);
            btnMonth.setFont(fntViewBtns);
            btnYear = new JButton("Y");
            fntViewBtns = new Font(lblEastLowerTitle.getName(), Font.PLAIN, 8);
            btnYear.setFont(fntViewBtns);

            btnDay.addActionListener(this);
            btnWeek.addActionListener(this);
            btnMultiWeek.addActionListener(this);
            btnMonth.addActionListener(this);
            btnYear.addActionListener(this);

            pnlCenterNorthRight.add(btnDay, pnlCenterNorthRight);
            pnlCenterNorthRight.add(btnWeek, pnlCenterNorthRight);
            pnlCenterNorthRight.add(btnMultiWeek, pnlCenterNorthRight);
            pnlCenterNorthRight.add(btnMonth, pnlCenterNorthRight);
            pnlCenterNorthRight.add(btnYear, pnlCenterNorthRight);

            //**********************************************************************
            //Center Center Panel
            //**********************************************************************
            pnlCenterCenter = new JPanel();
            pnlCenterCenter.setLayout(new BoxLayout(pnlCenterCenter, BoxLayout.Y_AXIS));

            //**********************************************************************
            //Center Center - Day View
            pnlCenterCenterDay = new JPanel();
            pnlCenterCenterDay.setLayout(new BoxLayout(pnlCenterCenterDay, BoxLayout.Y_AXIS));
            GenerateDay();
            pnlCenterCenterDay.setVisible(false);

            //**********************************************************************
            //Center Center - Week View
            pnlCenterCenterWeek = new JPanel(new GridLayout(1, 7));
            GenerateWeek();
            pnlCenterCenterWeek.setVisible(false);

            //**********************************************************************
            //Center Center - Multi-Week View
            pnlCenterCenterMWeek = new JPanel(new GridLayout(3, 7));
            GenerateMWeek();
            pnlCenterCenterMWeek.setVisible(true);

            //**********************************************************************
            //Center Center - Month View
            // sets the center panel to a gridlayout of Number of weeks by the number of days in a week
            pnlCenterCenterMonth = new JPanel(new GridLayout(curDate.getActualMaximum(curDate.WEEK_OF_MONTH), 7));
            GenerateMonth();
            pnlCenterCenterMonth.setVisible(false);

            ///**********************************************************************
            //Center Center - Year View
            yearLayout = new GridBagLayout();
            pnlCenterCenterYear = new JPanel(yearLayout);
            GenerateYear();
            pnlCenterCenterYear.setVisible(false);

            //**********************************************************************
            //Center South Panel
            //**********************************************************************
            pnlCenterSouth = new JPanel(new FlowLayout());
            pnlCenterSouth.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            JPanel pnlDates = new JPanel(new GridLayout(1,3));
            btnIncDate = new JButton(">");
            btnIncDate.addActionListener(this);
            btnDecDate = new JButton("<");
            btnDecDate.addActionListener(this);
            lblIncDate = new JLabel(stringIncDate);
            lblIncDate.setForeground(Color.GRAY);
            lblIncDate.setHorizontalAlignment(JLabel.CENTER);
            lblCurrDate = new JLabel(stringCurDate);
            lblCurrDate.setFont(new Font("Dialog", Font.BOLD, 20));
            lblDecDate = new JLabel(stringDecDate);
            lblDecDate.setForeground(Color.GRAY);
            lblDecDate.setHorizontalAlignment(JLabel.CENTER);
            pnlDates.add(lblDecDate);
            pnlDates.add(lblCurrDate);
            pnlDates.add(lblIncDate);
            
            //**********************************************************************
            //*
            //*     Add Panels to BorderLayout Applet
            //*
            //**********************************************************************
            //East
            pnlEastMain.add(pnlEastUpperMain);
            pnlEastMain.add(pnlEastLowerMain);
            //West
            pnlWestMain.add(pnlWestUpperMain);
            pnlWestMain.add(pnlWestLowerMain);
            //Center
            pnlCenterNorth.add(pnlCenterNorthLeft);
            pnlCenterNorth.add(pnlCenterNorthMiddle);
            pnlCenterNorth.add(pnlCenterNorthRight);
            pnlCenterCenter.add(pnlCenterCenterDay);
            pnlCenterCenter.add(pnlCenterCenterWeek);
            pnlCenterCenter.add(pnlCenterCenterMWeek);
            pnlCenterCenter.add(pnlCenterCenterMonth);
            pnlCenterCenter.add(pnlCenterCenterYear);
            pnlCenterSouth.add(btnDecDate);
            pnlCenterSouth.add(pnlDates);
            //pnlCenterSouth.add(lblCurrDate);
            //pnlCenterSouth.add(lblIncDate);
            pnlCenterSouth.add(btnIncDate);
            pnlCenterMain.add(pnlCenterNorth, BorderLayout.NORTH);
            pnlCenterMain.add(pnlCenterCenter, BorderLayout.CENTER);
            pnlCenterMain.add(pnlCenterSouth, BorderLayout.SOUTH);
            //Main           
            pnlMain.add(pnlWestMain);
            pnlMain.add(pnlCenterMain);
            pnlMain.add(pnlEastMain);
            add(pnlMain);
            isFirstRun = false;
            
            if ( deleteCals )
            {
                sampleUser.removeCalendar(1);
                vectorCalendar.remove(0);
                ReloadCalendars();
            }
            if ( deleteContacts )
            {
                    sampleUser.editContact(indexSelectedContact, "", "", "", "", "", "", "", "", "", "");
                    cbContactList.insertItemAt("", cbContactList.getSelectedIndex());
                    cbContactList.removeItemAt(cbContactList.getSelectedIndex());
            }
        } 
    }
   
    @Override
    public void paint(Graphics g)
    {
       super.paint(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        //Login Button
        if(e.getSource() == btnLogin)
        {
            //if (database.verifyUser(txtUsername.getText(), txtPassword.getText()))
            //{
                userValidated  = true;
                lblError.setVisible(true);
                lblError.setText("Welcome!");
                pnlLoginMain.setVisible(false);
                pnlLoginMain.remove(btnLogin);
                init();
                repaint();
            //}
            //else
            //{
            //    lblError.setVisible(true);
           // }
        }
        if(e.getSource() == btnLogout)
        {
            if (!uploadContacts)
            {
                sampleUser.getUserContacts().clear();
            }
            database.saveUserData(sampleUser);
            if (!uploadContacts)
            {
                sampleUser.addContact("", "","", "", "", "", "", "", "", "");
            }
            //sampleUser = new User();
            userValidated=false;
            //lblError.setText("");
            //txtUsername.setText("");
            //txtPassword.setText("");
            //pnlLoginMain.setVisible(true);
            //pnlMain.setVisible(false);
            //btnLogin.addActionListener(GUI.this);
            //init();
            //repaint();
        }
        //Button Add New Event
        if(e.getSource() == btnAddEvent)
        {
            HidePropertiesPanels();

            String minutes;

            Calendar rightNow = Calendar.getInstance();
            cbStartMonth.setSelectedItem((rightNow.get(Calendar.MONTH)+1) + "");
            cbStartDay.setSelectedItem((rightNow.get(Calendar.DAY_OF_MONTH))+"");
            cbStartYear.setSelectedItem((rightNow.get(Calendar.YEAR))+"");
            cbStartHour.setSelectedItem((rightNow.get(Calendar.HOUR_OF_DAY))+"");
            if (rightNow.get(Calendar.MINUTE) < 10)
            {
                minutes = "0" + rightNow.get(Calendar.MINUTE);
            }
            else
            {
                minutes = rightNow.get(Calendar.MINUTE) + "";
            }
            cbStartMinute.setSelectedItem(minutes);
            rightNow.add(Calendar.HOUR_OF_DAY, 1);
            cbEndMonth.setSelectedItem((rightNow.get(Calendar.MONTH)+1) +"");
            cbEndDay.setSelectedItem((rightNow.get(Calendar.DAY_OF_MONTH))+"");
            cbEndYear.setSelectedItem((rightNow.get(Calendar.YEAR))+"");
            cbEndHour.setSelectedItem((rightNow.get(Calendar.HOUR_OF_DAY))+"");
            if (rightNow.get(Calendar.MINUTE) < 10)
            {
                minutes = "0" + rightNow.get(Calendar.MINUTE);
            }
            else
            {
                minutes = rightNow.get(Calendar.MINUTE) +"";
            }
            cbEndMinute.setSelectedItem(minutes);
            txtQuickLocation.setText("");
            txtQuickTitle.setText("");
            txtQuickNote.setText("");

            pnlQuick.setVisible(true);
        }
        //Button Goes to Today
        else if(e.getSource() == btnToday)
        {
            curDate = Calendar.getInstance();
            decDate = Calendar.getInstance();
            incDate = Calendar.getInstance();
            switch ( calInterval )
            {
                case 1: // day
                    decDate.add(Calendar.DAY_OF_MONTH, -1);
                    incDate.add(Calendar.DAY_OF_MONTH, 1);
                break;
                case 2: // week
                    decDate.add(Calendar.DAY_OF_MONTH, -7);
                    incDate.add(Calendar.DAY_OF_MONTH, 7);
                break;
                case 3: // mweek
                    decDate.add(Calendar.DAY_OF_MONTH, -21);
                    incDate.add(Calendar.DAY_OF_MONTH, 21);
                break;
                case 4: // month
                    decDate.add(Calendar.MONTH, -1);
                    incDate.add(Calendar.MONTH, 1);
                break;
                case 5: // year
                    decDate.add(Calendar.YEAR, -1);
                    incDate.add(Calendar.YEAR, 1);
                break;
            }
            GenerateViews();
            CalIntervalUpdate();
            UpdateCurrentTime();
        }
        //Button Loads Day View of Calendar
        else if(e.getSource() == btnDay)
        {      
            incDate.setTime(curDate.getTime());
            incDate.add(Calendar.DAY_OF_MONTH,+1);
            formatIncDate = new SimpleDateFormat("MMM dd");
            stringIncDate = formatIncDate.format(incDate.getTime());
            lblIncDate.setText(stringIncDate);
                
            formatCurDate = new SimpleDateFormat("MMM dd, yyyy");
            stringCurDate = formatCurDate.format(curDate.getTime());
            lblCurrDate.setText(stringCurDate);
   
            decDate.setTime(curDate.getTime());
            decDate.add(Calendar.DAY_OF_MONTH,-1);
            formatDecDate = new SimpleDateFormat("MMM dd");
            stringDecDate = formatDecDate.format(decDate.getTime());
            lblDecDate.setText(stringDecDate);
            
            previousCalInterval = calInterval;
            calInterval = 1;
            pnlCenterCenterDay.setVisible(true);
            pnlCenterCenterWeek.setVisible(false);
            pnlCenterCenterMWeek.setVisible(false);
            pnlCenterCenterMonth.setVisible(false);
            pnlCenterCenterYear.setVisible(false);
        }
        //Button Loads Week View of Calendar
        else if(e.getSource() == btnWeek)
        {
            
            incDate.setTime(curDate.getTime());
            incDate.add(Calendar.WEEK_OF_MONTH,+1);
            formatIncDate = new SimpleDateFormat("MMM dd");
            stringIncDate = formatIncDate.format(incDate.getTime());
            lblIncDate.setText(stringIncDate);
                
            formatCurDate = new SimpleDateFormat("MMM dd, yyyy");
            stringCurDate = formatCurDate.format(curDate.getTime());
            lblCurrDate.setText(stringCurDate);
   
            decDate.setTime(curDate.getTime());
            decDate.add(Calendar.WEEK_OF_MONTH,-1);
            formatDecDate = new SimpleDateFormat("MMM dd");
            stringDecDate = formatDecDate.format(decDate.getTime());
            lblDecDate.setText(stringDecDate);
            
            previousCalInterval = calInterval; 
            calInterval = 2;
            pnlCenterCenterDay.setVisible(false);
            pnlCenterCenterWeek.setVisible(true);
            pnlCenterCenterMWeek.setVisible(false);
            pnlCenterCenterMonth.setVisible(false);
            pnlCenterCenterYear.setVisible(false);
        }
        //Button Loads Multi Week Calendar View
        else if(e.getSource() == btnMultiWeek)
        {
            incDate.setTime(curDate.getTime());
            incDate.add(Calendar.WEEK_OF_MONTH,+3);
            formatIncDate = new SimpleDateFormat("MMM dd");
            stringIncDate = formatIncDate.format(incDate.getTime());
            lblIncDate.setText(stringIncDate);
                
            formatCurDate = new SimpleDateFormat("MMM dd, yyyy");
            stringCurDate = formatCurDate.format(curDate.getTime());
            lblCurrDate.setText(stringCurDate);
   
            decDate.setTime(curDate.getTime());
            decDate.add(Calendar.WEEK_OF_MONTH,-3);
            formatDecDate = new SimpleDateFormat("MMM dd");
            stringDecDate = formatDecDate.format(decDate.getTime());
            lblDecDate.setText(stringDecDate);
            
            previousCalInterval = calInterval;
            calInterval = 3;
            pnlCenterCenterDay.setVisible(false);
            pnlCenterCenterWeek.setVisible(false);
            pnlCenterCenterMWeek.setVisible(true);
            pnlCenterCenterMonth.setVisible(false);
            pnlCenterCenterYear.setVisible(false);
        }
        //Button Loads Month Calendar View
        else if(e.getSource() == btnMonth)
        {
            
            incDate.setTime(curDate.getTime());
            incDate.add(Calendar.MONTH,1);
            formatIncDate = new SimpleDateFormat("MMM");
            stringIncDate = formatIncDate.format(incDate.getTime());
            lblIncDate.setText(stringIncDate);
                
            formatCurDate = new SimpleDateFormat("MMM dd, yyyy");
            stringCurDate = formatCurDate.format(curDate.getTime());
            lblCurrDate.setText(stringCurDate);
   
            decDate.setTime(curDate.getTime());
            decDate.add(Calendar.MONTH,-1);
            formatDecDate = new SimpleDateFormat("MMM");
            stringDecDate = formatDecDate.format(decDate.getTime());
            lblDecDate.setText(stringDecDate);
            
            previousCalInterval = calInterval;
            calInterval = 4;
            pnlCenterCenterDay.setVisible(false);
            pnlCenterCenterWeek.setVisible(false);
            pnlCenterCenterMWeek.setVisible(false);
            pnlCenterCenterMonth.setVisible(true);
            pnlCenterCenterYear.setVisible(false);
        }
        //Button Loads Year Calendar 
        else if(e.getSource() == btnYear)
        {
            
            incDate.setTime(curDate.getTime());
            incDate.add(Calendar.YEAR,+1);
            formatIncDate = new SimpleDateFormat("yyyy");
            stringIncDate = formatIncDate.format(incDate.getTime());
            lblIncDate.setText(stringIncDate);
                
            formatCurDate = new SimpleDateFormat("MMM dd, yyyy");
            stringCurDate = formatCurDate.format(curDate.getTime());
            lblCurrDate.setText(stringCurDate);
   
            decDate.setTime(curDate.getTime());
            decDate.add(Calendar.YEAR,-1);
            formatDecDate = new SimpleDateFormat("yyyy");
            stringDecDate = formatDecDate.format(decDate.getTime());
            lblDecDate.setText(stringDecDate);
            
            previousCalInterval = calInterval;
            calInterval = 5;
            pnlCenterCenterDay.setVisible(false);
            pnlCenterCenterWeek.setVisible(false);
            pnlCenterCenterMWeek.setVisible(false);
            pnlCenterCenterMonth.setVisible(false);
            pnlCenterCenterYear.setVisible(true);
        }
        //Button Increments Date by interval set by current view
        else if(e.getSource() == btnIncDate)
        {
            //Inc Day
            if(calInterval == 1) 
            {
                CalIntervalAddDay(); 
            }
            // Week
            else if (calInterval == 2) //Week
            {
                CalIntervalAddWeek();
            }
            else if (calInterval == 3) //MWeek
            {
                CalIntervalAddMWeek();
            }
            else if (calInterval == 4) //Month
            {
                CalIntervalAddMonth();
            }
            else if (calInterval == 5) //Year
            {
                CalIntervalAddYear();
            }
            GenerateViews();
        }
        //Button decrements date by interval set by current view
        else if(e.getSource() == btnDecDate)
        {
            if(calInterval == 1) //Day
            {
                CalIntervalMinusDay();
            }
            else if (calInterval == 2) //Week
            {
                CalIntervalMinusWeek();
            }
            else if (calInterval == 3) //MWeek
            {
                CalIntervalMinusMWeek();
            }
            else if (calInterval == 4) //Month
            {
                CalIntervalMinusMonth();
            }
            else if (calInterval == 5) //Year
            {
                CalIntervalMinusYear();
            }
            GenerateViews();
        }
        //Button Load Edit Properties Panel using the current contact shown in the combobox
        else if(e.getSource() == btnEditContact)
        {
            boolNewContact =false;
            boolEditContact =true;
            
            HidePropertiesPanels();
            pnlEditContactPanel.setVisible(true);
            Contacts tempContact = new Contacts();
            tempContact = sampleUser.getSelectContact(cbContactList.getSelectedIndex());
            txtPrefix.setText(tempContact.getPrefix());
            txtFirstName.setText(tempContact.getFirstName());
            txtNickName.setText(tempContact.getNickName());
            txtMiddleName.setText(tempContact.getMiddleName());
            txtLastName.setText(tempContact.getLastName());
            txtSuffix.setText(tempContact.getSuffix());
            txtBirthDate.setText(tempContact.getBirthdate());
            
            txtJobTitle.setText(tempContact.getJobTitle());
            txtDepartment.setText(tempContact.getDepartment());
            txtCompany.setText(tempContact.getCompany());
           
            vectorAddressTitles = sampleUser.getVectorContactAddress(cbContactList.getSelectedIndex());
            vectorPhoneTitles = sampleUser.getVectorContactPhoneNum(cbContactList.getSelectedIndex());       
            vectorEmailTitles = sampleUser.getVectorContactEmail(cbContactList.getSelectedIndex());
            vectorSocialTitles = sampleUser.getVectorContactSM(cbContactList.getSelectedIndex());
            vectorCustomTitles = sampleUser.getVectorContactCustom(cbContactList.getSelectedIndex());
        }
        //Button Loads Add Contact Properties Panel
        else if(e.getSource() == btnAddContact)
        {
            boolNewContact =true;
            boolEditContact =false;
            HidePropertiesPanels();
            pnlEditContactPanel.setVisible(true);
            txtPrefix.setText("");
            txtFirstName.setText("");
            txtNickName.setText("");
            txtMiddleName.setText("");
            txtLastName.setText("");
            txtSuffix.setText("");
            txtBirthDate.setText("");            
            txtJobTitle.setText("");
            txtDepartment.setText("");
            txtCompany.setText("");
        }
        // Button Loads remove Contact panel, select contact to delete in contact
        else if(e.getSource() == btnRemoveContact)
        {
            HidePropertiesPanels();
            pnlDeleteContactPanel.setVisible(true);
        }
        else if(e.getSource() == btnSaveContact)
        {
            // determines if it is adding a new contact or editing one
            if(boolNewContact)
            {
                LoadContactData();
                sampleUser.addContact(txtPrefix.getText(), txtFirstName.getText(), txtNickName.getText(), txtMiddleName.getText(), txtLastName.getText(), txtSuffix.getText(), txtJobTitle.getText(), txtDepartment.getText(), txtCompany.getText(), txtBirthDate.getText());
                cbContactList.addItem(txtFirstName.getText()+" "+txtLastName.getText());
                HidePropertiesPanels();
                pnlPropertiesDefault.setVisible(true);
                uploadContacts = true;
            }
            //Determines if your editing the contact
            else if(boolEditContact)
            {
                LoadContactData();
                //cbContactList.setSelectedIndex(indexSelectedContact);
                sampleUser.editContact(cbContactList.getSelectedIndex(),txtPrefix.getText(), txtFirstName.getText(), txtNickName.getText(), txtMiddleName.getText(), txtLastName.getText(), txtSuffix.getText(), txtJobTitle.getText(), txtDepartment.getText(), txtCompany.getText(), txtBirthDate.getText());
                //cbContactList.insertItemAt(txtFirstName.getText()+" "+txtLastName.getText(), cbContactList.getSelectedIndex());
                //cbContactList.removeItemAt(cbContactList.getSelectedIndex());
                HidePropertiesPanels();
                pnlPropertiesDefault.setVisible(true);
                uploadContacts = true;
            }
        }
        //Button if you cancel 
        else if(e.getSource() == btnCancelContact)
        {
            HidePropertiesPanels();
            boolNewContact = false;
            boolEditContact = false;
            pnlEditContactPanel.setVisible(false);
            Contacts tempContact = new Contacts();
            txtPrefix.setText(tempContact.getPrefix());
            txtFirstName.setText(tempContact.getFirstName());
            txtNickName.setText(tempContact.getNickName());
            txtMiddleName.setText(tempContact.getMiddleName());
            txtLastName.setText(tempContact.getLastName());
            txtSuffix.setText(tempContact.getSuffix());
            txtBirthDate.setText(tempContact.getBirthdate());            
            txtJobTitle.setText(tempContact.getJobTitle());
            txtDepartment.setText(tempContact.getDepartment());
            txtCompany.setText(tempContact.getCompany());
            pnlPropertiesDefault.setVisible(true);
        }
        
        //**********************************************************************
        //  
        //  Properties - Add and Remove Calendar
        //
        //********************************************************************** 
        //Load Panel Button - Add New Calendar
        else if(e.getSource() == btnAddCal)
        {
            HidePropertiesPanels();
            txtNewCalendarName.setText("");
            pnlNewCalendar.setVisible(true);
        }
        //Pnl Button - Add New Calendar - Save
        else if(e.getSource() == btnNewCalendarSave)
        {
            sampleUser.addCalendar(txtNewCalendarName.getText());
            vectorCalendar.add(txtNewCalendarName.getText());
            ReloadCalendars();
            //pnlProperties.revalidate();
            //validate();
            //repaint();
            pnlNewCalendar.setVisible(false); 
        }
        //Pnl Button - Add New Calendar - Cancel
        else if(e.getSource() == btnNewCalendarCancel)
        {
            HidePropertiesPanels();
            pnlPropertiesDefault.setVisible(true);
        }
        //Load Panel Button - Delete Calendar
        else if(e.getSource() == btnDeleteCal)
        {
            HidePropertiesPanels();
            pnlDeleteCalendar.setVisible(true);
        }
        //Button - Delete Calendar - Delete
        else if(e.getSource() == btnDeleteCalendar)
        {
            sampleUser.removeCalendar(cbCalendarList.getSelectedIndex()+1);
            vectorCalendar.remove(cbCalendarList.getSelectedIndex());
            //sampleUser.printCalendarList(); 
            ReloadCalendars();
            pnlDeleteCalendar.setVisible(false);
        }
        //Button - Delete Calendar - Cancel
        else if(e.getSource() == btnCancelCalendar)
        {
            HidePropertiesPanels();
            pnlPropertiesDefault.setVisible(true);
        }

        else if(e.getSource() == agendaDelete)
        {
            if ( agendaJList.getSelectedIndex() == -1 )
            {
                return;
            }
            HidePropertiesPanels();
            DeleteEvent((Event)agendaObjList.get(agendaJList.getSelectedIndex()));
            ReloadAgenda();
            GenerateViews();
        }

        else if(e.getSource() == agendaLoad)
        {
            if ( agendaJList.getSelectedIndex() == -1 )
            {
                return;
            }
            HidePropertiesPanels();
            this.editEvent = true;
            
            agendaLoadedIndex = agendaJList.getSelectedIndex();
            Quick quickEvent = (Quick)agendaObjList.get(agendaLoadedIndex);
            String minutes;
            int jCalIndex = FindJCal((Event)quickEvent);
            int eventGroupIndex = FindEventGroup((Event)quickEvent, jCalIndex);
            cbxQuickCalendar.setSelectedIndex(jCalIndex);
            jlQuickEventGroup.setSelectedIndex(eventGroupIndex);
            cbStartMonth.setSelectedItem((quickEvent.getStart().get(Calendar.MONTH)+1) + "");
            cbStartDay.setSelectedItem((quickEvent.getStart().get(Calendar.DAY_OF_MONTH))+"");
            cbStartYear.setSelectedItem((quickEvent.getStart().get(Calendar.YEAR))+"");
            cbStartHour.setSelectedItem((quickEvent.getStart().get(Calendar.HOUR_OF_DAY))+"");
            if (quickEvent.getStart().get(Calendar.MINUTE) < 10)
            {
                minutes = "0" + quickEvent.getStart().get(Calendar.MINUTE);
            }
            else
            {
                minutes = quickEvent.getStart().get(Calendar.MINUTE) + "";
            }
            cbStartMinute.setSelectedItem(minutes);
            cbEndMonth.setSelectedItem((quickEvent.getEnd().get(Calendar.MONTH)+1) +"");
            cbEndDay.setSelectedItem((quickEvent.getEnd().get(Calendar.DAY_OF_MONTH))+"");
            cbEndYear.setSelectedItem((quickEvent.getEnd().get(Calendar.YEAR))+"");
            cbEndHour.setSelectedItem((quickEvent.getEnd().get(Calendar.HOUR_OF_DAY))+"");
            if (quickEvent.getEnd().get(Calendar.MINUTE) < 10)
            {
                minutes = "0" + quickEvent.getEnd().get(Calendar.MINUTE);
            }
            else
            {
                minutes = quickEvent.getEnd().get(Calendar.MINUTE) +"";
            }
            cbEndMinute.setSelectedItem(minutes);
            txtQuickLocation.setText(quickEvent.getLocation());
            txtQuickTitle.setText(quickEvent.getEventTitle());
            txtQuickNote.setText(quickEvent.getEventNotes());
            pnlQuick.setVisible(true);
        }

        else if(e.getSource() == quickSaveBtn)
        {
            Calendar start = Calendar.getInstance();
            Calendar end = Calendar.getInstance();
            String location = txtQuickLocation.getText();
            String title = txtQuickTitle.getText();
            String notes = txtQuickNote.getText();
            int jCalSelectedIndex = cbxQuickCalendar.getSelectedIndex();
            int eventGroupSelectedIndex = jlQuickEventGroup.getSelectedIndex();
            start.set(Calendar.MONTH, Integer.parseInt(MONTHS[cbStartMonth.getSelectedIndex()])-1);
            start.set(Calendar.DAY_OF_MONTH, Integer.parseInt((DAYS_OF_MONTH[cbStartDay.getSelectedIndex()])));
            start.set(Calendar.YEAR, Integer.parseInt((YEARS[cbStartYear.getSelectedIndex()])));
            start.set(Calendar.HOUR_OF_DAY, Integer.parseInt((HOURS[cbStartHour.getSelectedIndex()])));
            start.set(Calendar.MINUTE, Integer.parseInt((MINUTES[cbStartMinute.getSelectedIndex()])));
            end.set(Calendar.MONTH, Integer.parseInt(MONTHS[cbEndMonth.getSelectedIndex()])-1);
            end.set(Calendar.DAY_OF_MONTH, Integer.parseInt((DAYS_OF_MONTH[cbEndMonth.getSelectedIndex()])));
            end.set(Calendar.YEAR, Integer.parseInt(YEARS[cbEndYear.getSelectedIndex()]));
            end.set(Calendar.HOUR_OF_DAY, Integer.parseInt(HOURS[cbEndHour.getSelectedIndex()]));
            end.set(Calendar.MINUTE, Integer.parseInt(MINUTES[cbEndMinute.getSelectedIndex()]));

            if ( editEvent )
            {
                Quick quick = (Quick)agendaObjList.get(agendaLoadedIndex);
                int currentJCalIndex = FindJCal((Event)quick);
                int currentEventGroupIndex = FindEventGroup((Event)quick, currentJCalIndex);
                if (  currentJCalIndex != jCalSelectedIndex ||  currentEventGroupIndex != eventGroupSelectedIndex )
                {
                    DeleteEvent((Event)quick);
                    sampleUser.addEvent(jCalSelectedIndex+1, eventGroupSelectedIndex+1, new Quick(start, end, location, false, title, notes, true, false, 0, 15));
                }
                else
                {
                    quick.setStart(start);
                    quick.setEnd(end);
                    quick.setLocation(location);
                    quick.setEventTitle(title);
                    quick.setEventNotes(notes);
                }
            }
            else
            {
                sampleUser.addEvent(jCalSelectedIndex+1, eventGroupSelectedIndex+1, new Quick(start, end, location, false, title, notes, true, false, 0, 15)); 
            }

            GenerateViews();
            ReloadAgenda();
            HidePropertiesPanels();
            pnlPropertiesDefault.setVisible(true);
        }

        else if(e.getSource() == quickCancelBtn)
        {
            HidePropertiesPanels();
            pnlPropertiesDefault.setVisible(true);
        }
        
        //**********************************************************************
        //  
        //  Properties - Add and Remove Event Group
        //
        //********************************************************************** 
        //Button - Add New Event Group
        else if(e.getSource() == btnAddEGroup)
        {
            HidePropertiesPanels();
            txtNewEGName.setText("");
            pnlAddEventGroup.setVisible(true);
        }
        //Button - Add New Event Group - Save
        else if(e.getSource() == btnNewEGSave)
        { 
            sampleUser.addCalendarEventGroup(cbCalendarListAddEG.getSelectedIndex()+1,txtNewEGName.getText());
            ReloadCalendars(); 
            HidePropertiesPanels();
            pnlPropertiesDefault.setVisible(true);
        }
        //Button - Add New Event Group - Cancel
        else if(e.getSource() == btnNewEGCancel)
        {  
            HidePropertiesPanels();
            pnlPropertiesDefault.setVisible(true);
        }
        //Button - Delete Event Group
        else if(e.getSource() == btnDeleteEGroup)
        {
            HidePropertiesPanels();
            pnlDeleteEventGroup.setVisible(true);
        }
        //CMBX - Update Event Group
        else if (e.getSource() == cbDeleteCalendarList)
        {
            vectorEventGroup = sampleUser.getVectorEGList(cbDeleteCalendarList.getSelectedIndex());
            cbEventGroupList.setListData(vectorEventGroup);
            cbEventGroupList.revalidate();
            pnlDeleteEventGroup.revalidate();
            validate();
            repaint();
        }
        //Button - Delete Event Group - Delete
        else if(e.getSource() == btnDeleteEG)
        {
            sampleUser.removeCalendarEventGroup(cbDeleteCalendarList.getSelectedIndex()+1, cbEventGroupList.getSelectedIndex()+2);
            //vectorEventGroup.remove(cbEventGroupList.getSelectedIndex()+1);
            ReloadCalendars();
            pnlDeleteEventGroup.setVisible(false);
        }
        //Delete Event Group - Cancel
        else if(e.getSource() == btnCancelEG)
        {
            HidePropertiesPanels();
            pnlPropertiesDefault.setVisible(true);
        }
        else if (e.getSource() == cbxQuickCalendar)
        {
            vectorEventGroup = sampleUser.getVectorEGList(cbxQuickCalendar.getSelectedIndex());
            jlQuickEventGroup.setListData(vectorEventGroup);
            jlQuickEventGroup.revalidate();
            pnlQuick.revalidate();
            validate();
            repaint();
        }
        else if(e.getSource() == btnDeleteDeleteContact)
        {
            indexSelectedContact = cbxDeleteContactList.getSelectedIndex();
            
            // removes contact if item count is greater than one otherwise sets remaining contact values to nothing
            if(cbContactList.getItemCount()>1)
            {
                cbContactList.removeItemAt(indexSelectedContact);
                sampleUser.removeContact(indexSelectedContact);
            }
            else if(cbContactList.getItemCount()==1)
            {
                sampleUser.editContact(indexSelectedContact, "", "", "", "", "", "", "", "", "", "");
                cbContactList.insertItemAt("", cbContactList.getSelectedIndex());
                cbContactList.removeItemAt(cbContactList.getSelectedIndex());                
            }
            HidePropertiesPanels();
            pnlPropertiesDefault.setVisible(true);
        }
        else if(e.getSource() == btnDeleteCancelContact)
        {
            HidePropertiesPanels();
            pnlPropertiesDefault.setVisible(true);
        }  
        
        //**********************************************************************
        //  
        //  Properties - Contacts - !SC!
        //
        //********************************************************************** 
        //Address
        else if(e.getSource() == btnSaveAddress && tabAddSelected)
        {
            
            curContact.addAddress(txtAddressLabel.getText(),Double.parseDouble(txtStreetNum.getText()),txtStreetName.getText(),txtCity.getText(),txtState.getText(),Double.parseDouble(txtZipcode.getText()),txtCountry.getText());
            vectorAddressTitles.addElement(txtAddressLabel.getText());
            txtAddressLabel.setText("");
            txtStreetNum.setText("");
            txtStreetName.setText("");
            txtCity.setText("");
            txtState.setText("");
            txtZipcode.setText("");
            txtCountry.setText("");
            LoadContactData();
        }
        else if(e.getSource() == btnSaveAddress && tabEditSelected)
        {
            curContact = sampleUser.getSelectContact(cbContactList.getSelectedIndex());
            curContact.editAddress(cmbEditTitlesAddress.getSelectedIndex(),txtAddressLabel.getText(),Double.parseDouble(txtStreetNum.getText()),txtStreetName.getText(),txtCity.getText(),txtState.getText(),Double.parseDouble(txtZipcode.getText()),txtCountry.getText());
        }
        else if(e.getSource() == btnDeleteAddress && tabDeleteSelected)
        {
            curContact.removeAddress(cmbDeleteTitlesAddress.getSelectedIndex());
            vectorAddressTitles.removeAllElements();
            vectorAddressTitles.addAll(sampleUser.getVectorContactAddress(cbContactList.getSelectedIndex()));
        }
        //********************************************************************** 
        //Phone Number
        else if(e.getSource() == btnSavePhone && tabAddSelected)
        {
            curContact.addPhoneNumber(txtPhoneLabel.getText(), txtPhoneNumber.getText(), Double.parseDouble(txtPhoneExtension.getText()));
            vectorPhoneTitles.addElement(txtPhoneLabel.getText());
            txtPhoneLabel.setText("");
            txtPhoneNumber.setText("");
            txtPhoneExtension.setText("");
            LoadContactData();
        }
        else if(e.getSource() == btnSavePhone && tabEditSelected)
        {
            curContact = sampleUser.getSelectContact(cbContactList.getSelectedIndex());
            curContact.editPhoneNumber(cmbEditTitlesPhone.getSelectedIndex(),txtPhoneLabel.getText(),txtPhoneNumber.getText(),Double.parseDouble(txtPhoneExtension.getText()));
        }
        else if(e.getSource() == btnDeletePhone && tabDeleteSelected)
        {
            curContact.removePhoneNumber(cmbDeleteTitlesPhone.getSelectedIndex());
            vectorPhoneTitles.removeAllElements();
            vectorPhoneTitles.addAll(sampleUser.getVectorContactPhoneNum(cbContactList.getSelectedIndex()));
        }
        //**********************************************************************
        // Custom
        else if(e.getSource() == btnSaveCustom && tabAddSelected)
        {
            curContact.addCustomList(txtCustomLabel.getText(), txtCustomBody.getText());
            vectorCustomTitles.addElement(txtCustomLabel.getText());
            txtCustomLabel.setText("");
            txtCustomBody.setText("");
            LoadContactData();
        }
        else if(e.getSource() == btnSaveCustom && tabEditSelected)
        {
            curContact = sampleUser.getContactAtIndex(cbContactList.getSelectedIndex());
            curContact.editCustomList(cmbEditTitlesCustom.getSelectedIndex(), txtCustomLabel.getText(), txtCustomBody.getText());
        }
        else if(e.getSource() == btnDeleteCustom && tabDeleteSelected)
        {
            curContact.removeCustomList(cmbDeleteTitlesCustom.getSelectedIndex());
            vectorCustomTitles.removeAllElements();
            vectorCustomTitles.addAll(sampleUser.getVectorContactCustom(cbContactList.getSelectedIndex()));
            System.out.println("herereeere");
        }
        //**********************************************************************
        // Social
        else if(e.getSource() == btnSaveSM && tabAddSelected)
        {
            curContact.addSocialMedia(txtSMLabel.getText(), txtSM.getText());
            vectorSocialTitles.addElement(txtSMLabel.getText());
            txtSMLabel.setText("");
            txtSM.setText("");
            LoadContactData();
        }
        else if(e.getSource() == btnSaveSM && tabEditSelected)
        {
            curContact = sampleUser.getContactAtIndex(cbContactList.getSelectedIndex());
            curContact.editSocialMedia(cmbEditTitlesSM.getSelectedIndex(), txtSMLabel.getText(), txtSM.getText());
        }
        else if(e.getSource() == btnDeleteSM && tabDeleteSelected)
        {
            curContact.removeSocialMedia(cmbDeleteTitlesSM.getSelectedIndex());
            vectorSocialTitles.removeAllElements();
            vectorSocialTitles.addAll(sampleUser.getVectorContactSM(cbContactList.getSelectedIndex()));
        }
        //**********************************************************************
        // Email
        else if(e.getSource() == btnSaveEmail && tabAddSelected)
        {
            curContact.addEmail(txtEmailLabel.getText(), txtEmail.getText());
            vectorEmailTitles.addElement(txtEmailLabel.getText());
            txtEmailLabel.setText("");
            txtEmail.setText("");
            LoadContactData();
        }
        else if(e.getSource() == btnSaveEmail && tabEditSelected)
        {
            curContact = sampleUser.getContactAtIndex(cbContactList.getSelectedIndex());
            curContact.editEmail(cmbEditTitlesEmail.getSelectedIndex(),txtEmailLabel.getText(), txtEmail.getText());
        }
        else if(e.getSource() == btnDeleteEmail && tabDeleteSelected)
        {
            curContact.removeEmail(cmbDeleteTitlesEmail.getSelectedIndex());
            vectorEmailTitles.removeAllElements();
            vectorEmailTitles.addAll(sampleUser.getVectorContactEmail(cbContactList.getSelectedIndex()));
        }
        
        
        
        
    }
    
    @Override
    public void itemStateChanged(ItemEvent e) {
        if(e.getSource() == cbContactList)
        {        
            LoadContactData();
        }
        else if(e.getSource() == cmbEditTitlesPhone)
        {
            PhoneNumber tempNum = sampleUser.getContactPhoneNumber(cbContactList.getSelectedIndex(),cmbEditTitlesPhone.getSelectedIndex());
            txtPhoneLabel.setText(tempNum.getNumberLabel());
            txtPhoneNumber.setText(tempNum.getNumber());
            txtPhoneExtension.setText(Double.toString(tempNum.getExtension()));
            System.out.print("cmb edit");
        }
        else if(e.getSource() == cmbEditTitlesCustom)
        {
            CustomList tempCustom = sampleUser.getContactCustom(cbContactList.getSelectedIndex(),cmbEditTitlesCustom.getSelectedIndex());
            txtCustomLabel.setText(tempCustom.getTitleDescriptor());
            txtCustomBody.setText(tempCustom.getBody());
        }
        else if(e.getSource() == cmbEditTitlesSM)
        {
            SocialMedia tempSM = sampleUser.getContactSM(cbContactList.getSelectedIndex(),cmbEditTitlesSM.getSelectedIndex());
            txtSMLabel.setText(tempSM.getSocialLabel());
            txtSM.setText(tempSM.getUsername());
        }
        else if(e.getSource() == cmbEditTitlesAddress)
        {
            Address tempAdddress = sampleUser.getContactAddress(cbContactList.getSelectedIndex(),cmbEditTitlesAddress.getSelectedIndex());
            txtAddressLabel.setText(tempAdddress.getAddressLabel());
            txtStreetNum.setText(Double.toString(tempAdddress.getStreetNumber()));
            txtStreetName.setText(tempAdddress.getStreetName());
            txtCity.setText(tempAdddress.getCity());
            txtState.setText(tempAdddress.getState());
            txtZipcode.setText(Double.toString(tempAdddress.getZipcode()));
            txtCountry.setText(tempAdddress.getCountry());  
        }
         else if(e.getSource() == cmbEditTitlesEmail)
        {
            Email tempEmail = sampleUser.getContactEmail(cbContactList.getSelectedIndex(),cmbEditTitlesEmail.getSelectedIndex());
            txtEmailLabel.setText(tempEmail.getAccountLabel());
            txtEmail.setText(tempEmail.getEmail());
        }

        if(e.getSource() == paneContactTabs)
        {
            System.out.print("sdfsa");
        }    
         
        //***************************************************************************************************
        //
        //  ITEM LISTEN EVENT HANDLING FOR GUI COMPONENTS WITHIN JCAL-E.GROUP-EVENT-[TYPE]
        //
        //***************************************************************************************************
        if (!isFirstRun && !skipListening) {
            for (JCal calLevel : sampleUser.getUserCalendars()) 
            {
                //***********************************************************************************************
                //Cal Tree Checkboxes
                //Desc: If calendar selected, selects all this cals' event groups
                if (!isFirstRun) {
                    if (e.getSource() == calLevel.getCbxCalTree() && e.getStateChange() == ItemEvent.SELECTED && !eventGroupClickedCalendar) {

                        eventGroupClickedCalendar = false;
                        calendarClickedCalendar = true;
                        System.out.println("cal clicked");
                        for (EventGroup eachEvent : calLevel.getEventGroup()) {
                            skipListening = true;
                            eachEvent.getCbxEGCalTree().doClick();
                            skipListening = false;
                        }

                    }
                }

                //***********************************************************************************************
                //Cal Tree Checkboxes
                //Desc: If calendar unselected, unselects all this cals' event groups
                if (e.getSource() == calLevel.getCbxCalTree() && e.getStateChange() == ItemEvent.DESELECTED) //&& !eventGroupClickedCalendar)
                {
                    eventGroupClickedCalendar = false;
                    calendarClickedCalendar = true;
                    System.out.println("cal clicked");
                    for (EventGroup eachEvent : calLevel.getEventGroup()) {
                        if (eachEvent.getCbxEGCalTree().isSelected()) {
                            skipListening = true;
                            eachEvent.getCbxEGCalTree().doClick();
                            skipListening = false;
                        }
                    }
                }

            }

            if (!calendarClickedCalendar) 
            {
                System.out.print("inside event group loop");
                for(JCal selectEGCal : sampleUser.getUserCalendars())
                {       
                    for(EventGroup selectedEventGroups : selectEGCal.getEventGroup())
                    {
                        boolean foo = !(selectEGCal.getCbxCalTree().isSelected());
                        if (e.getSource() == selectedEventGroups.getCbxEGCalTree() && e.getStateChange() == ItemEvent.SELECTED && foo) // && !calendarClickedCalendar)
                        {
                            eventGroupClickedCalendar = true;
                            firstSelectionOccured = true;
                            calendarClickedCalendar = false;
                            skipListening = true;
                            selectEGCal.getCbxCalTree().doClick();
                            skipListening = false;
                        }
                        else
                        {
                            firstSelectionOccured = false;
                        }
                    }
                }
                
                if(!firstSelectionOccured)
                {
                    for (JCal calLevelEG : sampleUser.getUserCalendars()) 
                    {
                        boolean eventsSelected = false;
                        boolean eventGroupDeselected= false;
                        for (EventGroup egtempEGAction : calLevelEG.getEventGroup()) 
                        {
                            boolean cbxSelected = egtempEGAction.getCbxEGCalTree().isSelected();
                            if (cbxSelected) 
                            {
                                eventsSelected = true;
                                System.out.println(egtempEGAction.getCbxEGCalTree().getName() + "is selected.");                          
                            }
                            if(e.getSource() == egtempEGAction.getCbxEGCalTree() && e.getStateChange() == ItemEvent.DESELECTED)
                            {
                            
                                eventGroupDeselected = true;
                            }
                            
                        }
                        boolean tempbool = (!eventsSelected);
                        if (tempbool && eventGroupDeselected) // && (!calendarClickedCalendar))
                        {
                            eventGroupClickedCalendar = true;
                            calendarClickedCalendar = false;
                            skipListening = true;
                            calLevelEG.getCbxCalTree().doClick();
                            skipListening = false;
                            eventsSelected = false;
                        }
                        
                        //eventsSelected = false;
                    }
                    firstSelectionOccured =false;
                }

            }
            calendarClickedCalendar = false;
            eventGroupClickedCalendar = false;
            if ( !isFirstRun )
            {
                GenerateViews();
                ReloadAgenda();
            }
        }
    }
    
    boolean eventGroupClickedCalendar = false;
    boolean calendarClickedCalendar = false;
    boolean firstSelectionOccured = false;
    boolean skipListening = false;
    
    public void LoadContactData() 
    {
        curContact = sampleUser.getSelectContact(cbContactList.getSelectedIndex());
        String stringName = (curContact.getPrefix() + " " + curContact.getFirstName() + " " + curContact.getNickName() + " " + curContact.getMiddleName() + " " + curContact.getLastName() + " " + curContact.getSuffix() + "\n");
        String stringWork = (curContact.getJobTitle() + "\n" + curContact.getDepartment() + "\n" + curContact.getCompany() + "\n\n");
        String stringAddresses = curContact.toStringAddress();
        String stringPhoneNumbers = curContact.toStringPhoneNumList();
        String stringEmails = curContact.toStringEmails();
        String stringSocialMedia = curContact.toStringSocialMedia();
        String stringCustomList = curContact.toStringCustomList();

        lblEmails.setText(stringName + stringWork + stringAddresses + stringPhoneNumbers + stringEmails + stringSocialMedia + stringCustomList);
    }

    public void ReloadCalendars()
    {
        //Load Calendars and Event Groups
        pnlCalTree.removeAll();
        
        for (JCal tempJCal : sampleUser.getUserCalendars()) {
            //Cal CheckBoxes
            tempJCal.getCbxCalTree().addItemListener(this);
            Font fontCalendar = new Font("Dialog", Font.BOLD, 14);
            tempJCal.getCbxCalTree().setFont(fontCalendar);
            pnlCalTree.add(tempJCal.getCbxCalTree(), pnlCalTree);
            
            if(isFirstRun)
            {
                tempJCal.getCbxCalTree().doClick();
                tempJCal.setCbxIsSelected(true);
            }
            
            for (EventGroup tempEventGroup : tempJCal.getEventGroup()) {
                //EG CheckBoxes
                tempEventGroup.getCbxEGCalTree().addItemListener(this);
                tempEventGroup.getCbxEGCalTree().setForeground(Color.DARK_GRAY);
                tempEventGroup.getCbxEGCalTree().setMargin(new Insets(0,20,0,0)); 
                pnlCalTree.add(tempEventGroup.getCbxEGCalTree(), pnlCalTree);

                if(isFirstRun)
                {
                    tempEventGroup.getCbxEGCalTree().doClick();
                    tempEventGroup.setCbxIsSelected(true);
                }
            }
        }
        pnlCalTree.revalidate();
        validate();
        repaint();
    }

    public void ReloadAgenda()
    {
            pnlAgenda.removeAll();
            pnlAgenda.setLayout(new BorderLayout());//new BoxLayout(pnlAgenda, BoxLayout.Y_AXIS));
            JPanel agendaButtons = new JPanel(new FlowLayout());

            agendaList = new Vector();
            agendaObjList = new Vector();
            
            JPanel pnlAgendaJL = new JPanel(new GridLayout(1,1));
            agendaJList = new JList(agendaList);
            agendaJList.setBackground(pnlEastMain.getBackground());
            pnlAgendaJL.add(agendaJList);
            agendaLoad = new JButton("Load");
            agendaLoad.addActionListener(this);
            agendaDelete = new JButton("Delete");
            agendaDelete.addActionListener(this);
            agendaButtons.add(agendaLoad);
            agendaButtons.add(agendaDelete);
            pnlAgenda.add(pnlAgendaJL, BorderLayout.CENTER);
            pnlAgenda.add(agendaButtons, BorderLayout.SOUTH);
            agendaJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

            for ( int abc = 0; abc < sampleUser.getUserCalendars().size(); abc++ )
            {
               for ( Event tempEvent : sampleUser.getUserCalendars().get(abc).getAllEvents() )
               {
                    if ( DisplayEvent(tempEvent) )
                    {
                        agendaList.add(tempEvent.getEventTitle());
                        agendaObjList.add(tempEvent);
                    }
               }
            }
            pnlAgenda.revalidate();
            validate();
            repaint();
    }
    
    public void HidePropertiesPanels()
    {
        //Properties Set Visibility 
        pnlPropertiesDefault.setVisible(false);
        pnlAppointment.setVisible(false);
        pnlDD.setVisible(false);
        pnlDeadline.setVisible(false);
        pnlFlight.setVisible(false);
        pnlMeeting.setVisible(false);
        pnlQuick.setVisible(false);
        pnlSchoolClass.setVisible(false);
        pnlVacation.setVisible(false);
        pnlNewCalendar.setVisible(false);
        pnlDeleteCalendar.setVisible(false);
        pnlAddEventGroup.setVisible(false);
        pnlDeleteEventGroup.setVisible(false);
        pnlEditContactPanel.setVisible(false);
        pnlDeleteContactPanel.setVisible(false);
        editEvent = false;
    }
    
    private ArrayList<Event> searchEventsInCal(Calendar curDate)
    { 
        ArrayList<Event> eventsList = new ArrayList();
        for ( int numOfCals = 0; numOfCals < sampleUser.getUserCalendars().size(); numOfCals++ )
        {
                ArrayList<Event> someEvents = sampleUser.getUserCalendars().get(numOfCals).getEventsOnDate(curDate);
                for ( int numOfEvents = 0; numOfEvents < someEvents.size(); numOfEvents++ )
                {
                    eventsList.add(someEvents.get(numOfEvents));
                }
            }
        return eventSorter(eventsList); 
    }
    private ArrayList<Event> eventSorter(ArrayList<Event> eventList)
    {
        ArrayList<Event> store = new ArrayList<Event>();
        while ( eventList.size() > 0 )
        {
            int lowest = 0;
            for ( int i = 0; i < eventList.size(); i++ )
            {
                Calendar cal1 = eventList.get(lowest).getStart();
                Calendar cal2 = eventList.get(i).getStart();
                if ( cal1.compareTo(cal2) >= 0)
                {
                    lowest = i;
                }
            }
            store.add(eventList.get(lowest));
            eventList.remove(lowest);
        }
        return store;
    }

    private void UpdateCurrentTime()
    {
        calTodaysDate = Calendar.getInstance();
        formatTodaysDate = new SimpleDateFormat("EEE, MMMMMMMMMMM dd, yyyy");
        stringTodaysDate = formatTodaysDate.format(calTodaysDate.getTime());
        lblTodaysDate.setText(stringTodaysDate);
    }

    private void CalIntervalUpdate()
    {
        stringIncDate = formatIncDate.format(incDate.getTime());
        lblIncDate.setText(stringIncDate);

        stringCurDate = formatCurDate.format(curDate.getTime());
        lblCurrDate.setText(stringCurDate);
   
        stringDecDate = formatDecDate.format(decDate.getTime());
        lblDecDate.setText(stringDecDate);
    }

    public void CalIntervalAddDay()
    {
        incDate.add(Calendar.DAY_OF_MONTH,1);
        //formatIncDate = new SimpleDateFormat("MMM dd");
        stringIncDate = formatIncDate.format(incDate.getTime());
        lblIncDate.setText(stringIncDate);
                
        curDate.add(Calendar.DAY_OF_MONTH,1);
        //formatCurDate = new SimpleDateFormat("MMM dd");
        stringCurDate = formatCurDate.format(curDate.getTime());
        lblCurrDate.setText(stringCurDate);
   
        decDate.add(Calendar.DAY_OF_MONTH,1);
        //formatDecDate = new SimpleDateFormat("MMM dd");
        stringDecDate = formatDecDate.format(decDate.getTime());
        lblDecDate.setText(stringDecDate);
    }
    
    
    public void CalIntervalMinusDay()
    {
        incDate.add(Calendar.DAY_OF_MONTH,-1);
        //formatIncDate = new SimpleDateFormat("MMM dd");
        stringIncDate = formatIncDate.format(incDate.getTime());
        lblIncDate.setText(stringIncDate);
                
        curDate.add(Calendar.DAY_OF_MONTH,-1);
        //formatCurDate = new SimpleDateFormat("MMM dd");
        stringCurDate = formatCurDate.format(curDate.getTime());
        lblCurrDate.setText(stringCurDate);
   
        decDate.add(Calendar.DAY_OF_MONTH,-1);
        //formatDecDate = new SimpleDateFormat("MMM dd");
        stringDecDate = formatDecDate.format(decDate.getTime());
        lblDecDate.setText(stringDecDate);
    }
    
    public void CalIntervalAddWeek()
    {
        incDate.add(Calendar.WEEK_OF_MONTH,1);
        //formatIncDate = new SimpleDateFormat("MMM dd");
        stringIncDate = formatIncDate.format(incDate.getTime());
        lblIncDate.setText(stringIncDate);
                
        curDate.add(Calendar.WEEK_OF_MONTH,1);
        //formatCurDate = new SimpleDateFormat("MMM dd");
        stringCurDate = formatCurDate.format(curDate.getTime());
        lblCurrDate.setText(stringCurDate);
   
        decDate.add(Calendar.WEEK_OF_MONTH,1);
        //formatDecDate = new SimpleDateFormat("MMM dd");
        stringDecDate = formatDecDate.format(decDate.getTime());
        lblDecDate.setText(stringDecDate);
    }
    
    public void CalIntervalMinusWeek()
    {
        incDate.add(Calendar.WEEK_OF_MONTH,-1);
        //formatIncDate = new SimpleDateFormat("MMM dd");
        stringIncDate = formatIncDate.format(incDate.getTime());
        lblIncDate.setText(stringIncDate);
                
        curDate.add(Calendar.WEEK_OF_MONTH,-1);
        //formatCurDate = new SimpleDateFormat("MMM dd");
        stringCurDate = formatCurDate.format(curDate.getTime());
        lblCurrDate.setText(stringCurDate);
   
        decDate.add(Calendar.WEEK_OF_MONTH,-1);
        //formatDecDate = new SimpleDateFormat("MMM dd");
        stringDecDate = formatDecDate.format(decDate.getTime());
        lblDecDate.setText(stringDecDate);
    }
    
    public void CalIntervalAddMWeek()
    {
        incDate.add(Calendar.WEEK_OF_MONTH,3);
        //formatIncDate = new SimpleDateFormat("MMM dd");
        stringIncDate = formatIncDate.format(incDate.getTime());
        lblIncDate.setText(stringIncDate);
                
        curDate.add(Calendar.WEEK_OF_MONTH,3);
        //formatCurDate = new SimpleDateFormat("MMM dd");
        stringCurDate = formatCurDate.format(curDate.getTime());
        lblCurrDate.setText(stringCurDate);
   
        decDate.add(Calendar.WEEK_OF_MONTH,3);
        //formatDecDate = new SimpleDateFormat("MMM dd");
        stringDecDate = formatDecDate.format(decDate.getTime());
        lblDecDate.setText(stringDecDate);
    }
    
    public void CalIntervalMinusMWeek()
    {
        incDate.add(Calendar.WEEK_OF_MONTH,-3);
        //formatIncDate = new SimpleDateFormat("MMM dd");
        stringIncDate = formatIncDate.format(incDate.getTime());
        lblIncDate.setText(stringIncDate);
                
        curDate.add(Calendar.WEEK_OF_MONTH,-3);
        //formatCurDate = new SimpleDateFormat("MMM dd");
        stringCurDate = formatCurDate.format(curDate.getTime());
        lblCurrDate.setText(stringCurDate);
   
        decDate.add(Calendar.WEEK_OF_MONTH,-3);
        //formatDecDate = new SimpleDateFormat("MMM dd");
        stringDecDate = formatDecDate.format(decDate.getTime());
        lblDecDate.setText(stringDecDate);
    }
    
    public void CalIntervalAddMonth()
    {
        incDate.add(Calendar.MONTH,1);
        //formatIncDate = new SimpleDateFormat("MMM dd");
        stringIncDate = formatIncDate.format(incDate.getTime());
        lblIncDate.setText(stringIncDate);
                
        curDate.add(Calendar.MONTH,1);
        //formatCurDate = new SimpleDateFormat("MMM dd");
        stringCurDate = formatCurDate.format(curDate.getTime());
        lblCurrDate.setText(stringCurDate);
   
        decDate.add(Calendar.MONTH,1);
        //formatDecDate = new SimpleDateFormat("MMM dd");
        stringDecDate = formatDecDate.format(decDate.getTime());
        lblDecDate.setText(stringDecDate);
    }
    
    public void CalIntervalMinusMonth()
    {
        incDate.add(Calendar.MONTH,-1);
        //formatIncDate = new SimpleDateFormat("MMM dd");
        stringIncDate = formatIncDate.format(incDate.getTime());
        lblIncDate.setText(stringIncDate);
                
        curDate.add(Calendar.MONTH,-1);
        //formatCurDate = new SimpleDateFormat("MMM dd");
        stringCurDate = formatCurDate.format(curDate.getTime());
        lblCurrDate.setText(stringCurDate);
   
        decDate.add(Calendar.MONTH,-1);
        //formatDecDate = new SimpleDateFormat("MMM dd");
        stringDecDate = formatDecDate.format(decDate.getTime());
        lblDecDate.setText(stringDecDate);
    }
    
    public void CalIntervalAddYear()
    {
        incDate.add(Calendar.YEAR,1);
        //formatIncDate = new SimpleDateFormat("MMM dd");
        stringIncDate = formatIncDate.format(incDate.getTime());
        lblIncDate.setText(stringIncDate);
                
        curDate.add(Calendar.YEAR,1);
        //formatCurDate = new SimpleDateFormat("MMM dd");
        stringCurDate = formatCurDate.format(curDate.getTime());
        lblCurrDate.setText(stringCurDate);
   
        decDate.add(Calendar.YEAR,1);
        //formatDecDate = new SimpleDateFormat("MMM dd");
        stringDecDate = formatDecDate.format(decDate.getTime());
        lblDecDate.setText(stringDecDate);
    }
    
    public void CalIntervalMinusYear()
    {
        incDate.add(Calendar.YEAR,-1);
        //formatIncDate = new SimpleDateFormat("MMM dd");
        stringIncDate = formatIncDate.format(incDate.getTime());
        lblIncDate.setText(stringIncDate);
                
        curDate.add(Calendar.YEAR,-1);
        //formatCurDate = new SimpleDateFormat("MMM dd");
        stringCurDate = formatCurDate.format(curDate.getTime());
        lblCurrDate.setText(stringCurDate);
   
        decDate.add(Calendar.YEAR,-1);
        //formatDecDate = new SimpleDateFormat("MMM dd");
        stringDecDate = formatDecDate.format(decDate.getTime());
        lblDecDate.setText(stringDecDate);
    }
    

    private void GenerateDay() {
        pnlCenterCenterDay.removeAll();
        ArrayList<Event> dayEvents = searchEventsInCal(curDate);

        String amPm1;
        String minutes1;
        String amPm2;
        String minutes2;
        String endDate;
        for ( int t = 0; t <= 23; t++ )
        {
            JPanel eventPanel = new JPanel();
            eventPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
            eventPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            eventPanel.setBackground(Color.WHITE);
            if ( dayEvents.size() > 0 && (t == dayEvents.get(0).getStart().get(Calendar.HOUR_OF_DAY) || t+1 > dayEvents.get(0).getStart().get(Calendar.HOUR_OF_DAY)) )
            {
                while ( dayEvents.size() > 0 && (t == dayEvents.get(0).getStart().get(Calendar.HOUR_OF_DAY) || t+1 > dayEvents.get(0).getStart().get(Calendar.HOUR_OF_DAY)) )
                {
                    Quick tempQuickEvent = (Quick)dayEvents.get(0);
                    if ( !DisplayEvent(dayEvents.get(0)) )
                    {
                        if ( t > 20 )
                        {

                        }
                        else if ( t > 12 )
                        {
                            eventPanel.add(new JLabel((t-12) + ":00PM"));
                            pnlCenterCenterDay.add(eventPanel);
                        }
                        else if ( t == 12 )
                        {
                            eventPanel.add(new JLabel(t + ":00PM"));
                            pnlCenterCenterDay.add(eventPanel);
                        }
                        else if ( t >= 8)
                        {
                            eventPanel.add(new JLabel(t + ":00AM"));
                            pnlCenterCenterDay.add(eventPanel);
                        }
                        dayEvents.remove(0);
                        continue;
                    }
                    if ( tempQuickEvent.getStart().get(Calendar.AM_PM) == Calendar.AM )
                    {
                        amPm1 = "AM";
                    }
                    else
                    {
                        amPm1 = "PM";
                    }
                    if ( tempQuickEvent.getStart().get(Calendar.MINUTE) < 10 )
                    {
                        minutes1 = "0" + tempQuickEvent.getStart().get(Calendar.MINUTE);
                    }
                    else
                    {
                        minutes1 = tempQuickEvent.getStart().get(Calendar.MINUTE) + "";
                    }
                    if ( tempQuickEvent.getEnd().get(Calendar.AM_PM) == Calendar.AM )
                    {
                        amPm2 = "AM";
                    }
                    else
                    {
                        amPm2 = "PM";
                    }
                    if ( tempQuickEvent.getEnd().get(Calendar.MINUTE) < 10 )
                    {
                        minutes2 = "0" + tempQuickEvent.getEnd().get(Calendar.MINUTE);
                    }
                    else
                    {
                        minutes2 = tempQuickEvent.getEnd().get(Calendar.MINUTE) + "";
                    }
                    SimpleDateFormat endDateFormat = new SimpleDateFormat("d MMM yyyy");
                    endDate = endDateFormat.format(tempQuickEvent.getEnd().getTime());
                    eventPanel.add(new JLabel(tempQuickEvent.getStart().get(Calendar.HOUR) + ":" + minutes1 + amPm1 + "-" + tempQuickEvent.getEnd().get(Calendar.HOUR) + ":" + minutes2 + amPm2 + "(" + endDate + ")"));
                    eventPanel.add(new JLabel(tempQuickEvent.getEventTitle() + " - " + tempQuickEvent.getLocation() + " : " + tempQuickEvent.getEventNotes()));
                    dayEvents.remove(0);
                }
               pnlCenterCenterDay.add(eventPanel);
            }
            else
            {
                if ( t > 20 )
                {

                }
                else if ( t > 12 )
                {
                    eventPanel.add(new JLabel((t-12) + ":00PM"));
                    pnlCenterCenterDay.add(eventPanel);
                }
                else if ( t == 12 )
                {
                    eventPanel.add(new JLabel(t + ":00PM"));
                    pnlCenterCenterDay.add(eventPanel);
                }
                else if ( t >= 8)
                {
                    eventPanel.add(new JLabel(t + ":00AM"));
                    pnlCenterCenterDay.add(eventPanel);
                }
            }
        }
    }   
    
    private void GenerateWeek() {
        pnlCenterCenterWeek.removeAll();
        Calendar tempCalObject = Calendar.getInstance();
        tempCalObject.set(curDate.get(Calendar.YEAR), curDate.get(Calendar.MONTH), curDate.get(Calendar.DAY_OF_MONTH));

        while (tempCalObject.get(tempCalObject.DAY_OF_WEEK) != Calendar.SUNDAY) {
            tempCalObject.add(tempCalObject.DAY_OF_MONTH, -1);
        }

        for (int cnting = 0; cnting < 7; cnting++) {
            JPanel cellPanel = new JPanel(new BorderLayout());
            JPanel dayPanel = new JPanel();
            dayPanel.setLayout(new BoxLayout(dayPanel, BoxLayout.Y_AXIS));
            dayPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            if ( tempCalObject.get(Calendar.DAY_OF_MONTH) == curDate.get(Calendar.DAY_OF_MONTH) && tempCalObject.get(Calendar.MONTH) == curDate.get(Calendar.MONTH) && tempCalObject.get(Calendar.YEAR) == curDate.get(Calendar.YEAR) )
            {
                dayPanel.setBackground(Color.CYAN);
            }
            else
            {
                dayPanel.setBackground(Color.WHITE);
            }
            JPanel dayOfWeekPanel = new JPanel(new FlowLayout());
            dayOfWeekPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            dayOfWeekPanel.add(new JLabel(DAYS_OF_WEEK[cnting]));
            cellPanel.add(dayOfWeekPanel, BorderLayout.NORTH);

            dayPanel.add(new JLabel(tempCalObject.get(tempCalObject.DAY_OF_MONTH) + ""));

            ArrayList<Event> weekEvents = searchEventsInCal(tempCalObject);
            for (int counterX = 0; counterX < weekEvents.size(); counterX++) {
                if ( !DisplayEvent(weekEvents.get(counterX)) )
                {
                    continue;
                }
                if (counterX < 29) {
                    dayPanel.add(new JLabel(weekEvents.get(counterX).getEventTitle()));
                } else if (counterX < 30) {
                    dayPanel.add(new JLabel("+" + (weekEvents.size() - 29) + " more"));
                }
            }
            cellPanel.add(dayPanel, BorderLayout.CENTER);
            pnlCenterCenterWeek.add(cellPanel);
            tempCalObject.add(tempCalObject.DAY_OF_MONTH, 1);
        }
    }

    private void GenerateMWeek() {
        pnlCenterCenterMWeek.removeAll();
        Calendar tmpCalObject = Calendar.getInstance();
        tmpCalObject.set(curDate.get(Calendar.YEAR), curDate.get(Calendar.MONTH), curDate.get(Calendar.DAY_OF_MONTH));

        while ( tmpCalObject.get(tmpCalObject.DAY_OF_WEEK) != Calendar.SUNDAY )
        {
            tmpCalObject.add(tmpCalObject.DAY_OF_MONTH, -1);
        }

        for ( int cnt = 0; cnt < 21; cnt++ )
        {
            JPanel cellPanel = new JPanel(new BorderLayout());
            JPanel dayPanel = new JPanel();
            dayPanel.setLayout(new BoxLayout(dayPanel, BoxLayout.Y_AXIS));
            dayPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            if ( tmpCalObject.get(Calendar.DAY_OF_MONTH) == curDate.get(Calendar.DAY_OF_MONTH) && tmpCalObject.get(Calendar.MONTH) == curDate.get(Calendar.MONTH) && tmpCalObject.get(Calendar.YEAR) == curDate.get(Calendar.YEAR) )
            {
                dayPanel.setBackground(Color.CYAN);
            }
            else
            {
                dayPanel.setBackground(Color.WHITE);
            }
            if ( cnt < 7 )
            {
                JPanel dayOfWeekPanel = new JPanel(new FlowLayout());
                dayOfWeekPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                dayOfWeekPanel.add(new JLabel(DAYS_OF_WEEK[cnt]));
                cellPanel.add(dayOfWeekPanel, BorderLayout.NORTH);
            }
            dayPanel.add(new JLabel(tmpCalObject.get(tmpCalObject.DAY_OF_MONTH)+""));

            ArrayList<Event> mWeekEvents = searchEventsInCal(tmpCalObject);
            for ( int counting = 0; counting < mWeekEvents.size(); counting++ )
            {
                if ( !DisplayEvent(mWeekEvents.get(counting)) )
                {
                    continue;
                }
                if ( cnt < 7 )
                {
                    if ( counting < 7 )
                    {
                        dayPanel.add(new JLabel(mWeekEvents.get(counting).getEventTitle()));
                    }
                    else if ( counting < 8 )
                    {
                        dayPanel.add(new JLabel("+" + (mWeekEvents.size()-7) + " more"));
                    }
                }
                else
                {
                    if ( counting < 8 )
                    {
                        dayPanel.add(new JLabel(mWeekEvents.get(counting).getEventTitle()));
                    }
                    else if ( counting < 9 )
                    {
                        dayPanel.add(new JLabel("+" + (mWeekEvents.size()-8) + " more"));
                    }
                }
            }
            cellPanel.add(dayPanel, BorderLayout.CENTER);

            pnlCenterCenterMWeek.add(cellPanel);
            tmpCalObject.add(tmpCalObject.DAY_OF_MONTH, 1);
        }
    }

    private void GenerateMonth() {
        pnlCenterCenterMonth.setLayout(new GridLayout(curDate.getActualMaximum(curDate.WEEK_OF_MONTH), 7));
        pnlCenterCenterMonth.removeAll();
        int firstDayCount = 0; // used to determine when to output the day of the week
        int cellsAdded = 0; // used to add cells to the end of the calendar
        // creates a new Calendar object
        Calendar tempCalObj = Calendar.getInstance();
        // sets tempCalObj to the year, month, and lowest date of the current month
        tempCalObj.set(curDate.get(curDate.YEAR), curDate.get(curDate.MONTH), curDate.getActualMinimum(curDate.DAY_OF_MONTH));
        // loop to output each day of the month to the calendar
        for ( boolean firstRun = true; cellsAdded < curDate.getActualMaximum(curDate.WEEK_OF_MONTH)*7; firstRun = false)
        {
                // does the following if this is the first run...
                while ( tempCalObj.get(tempCalObj.DAY_OF_WEEK) != Calendar.SUNDAY && firstRun )
                {
                    tempCalObj.add(tempCalObj.DAY_OF_MONTH, -1);
                }
                // creates a JPanel for the current day
                JPanel dayPanel = new JPanel();
                if ( tempCalObj.get(tempCalObj.MONTH) != curDate.get(curDate.MONTH) )
                {
                }
                // sets the panel to a BoxLayout
                dayPanel.setLayout(new BoxLayout(dayPanel, BoxLayout.Y_AXIS));
                // gives the cell a border
                dayPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                if ( tempCalObj.get(Calendar.DAY_OF_MONTH) == curDate.get(Calendar.DAY_OF_MONTH) && tempCalObj.get(Calendar.MONTH) == curDate.get(Calendar.MONTH) && tempCalObj.get(Calendar.YEAR) == curDate.get(Calendar.YEAR))
                {
                    dayPanel.setBackground(Color.CYAN);
                }
                else if ( tempCalObj.get(Calendar.MONTH) != curDate.get(Calendar.MONTH) )
                {
                    dayPanel.setForeground(Color.LIGHT_GRAY);
                }
                else
                {
                    dayPanel.setBackground(Color.WHITE);
                }
                // outputs the day number at the bottom of the cell
                dayPanel.add(new JLabel(tempCalObj.get(tempCalObj.DAY_OF_MONTH) + ""));
                // adds events to the cell

                ArrayList<Event> monthEvents = searchEventsInCal(tempCalObj);
                for ( int inc = 0; inc < monthEvents.size(); inc++)
                {
                    if ( !DisplayEvent(monthEvents.get(inc)) )
                    {
                        continue;
                    }
                    if ( firstDayCount < 7 )
                    {
                        if ( inc < 2 )
                        {
                            dayPanel.add(new JLabel(monthEvents.get(inc).getEventTitle()));
                        }
                        else if ( inc < 3 )
                        {
                            dayPanel.add(new JLabel("+" + (monthEvents.size()-2) + " more"));
                        }
                    }
                    else
                    {
                        if ( inc < 4 )
                        {
                            dayPanel.add(new JLabel(monthEvents.get(inc).getEventTitle()));
                        }
                        else if ( inc < 5 )
                        {
                            dayPanel.add(new JLabel("+" + (monthEvents.size()-4) + " more"));
                        }
                    }
                }

                if ( firstDayCount < 7 )
                {
                    JPanel cellPanel = new JPanel(new BorderLayout());
                    JPanel dayOfWeekPanel = new JPanel(new FlowLayout());
                    dayOfWeekPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

                    dayOfWeekPanel.add(new JLabel(DAYS_OF_WEEK[firstDayCount]));
                    cellPanel.add(dayOfWeekPanel, BorderLayout.NORTH);
                    cellPanel.add(dayPanel, BorderLayout.CENTER);
                    pnlCenterCenterMonth.add(cellPanel);
                    firstDayCount++;
                }
                else
                {
                    // adds the cell
                    pnlCenterCenterMonth.add(dayPanel);
                }
                cellsAdded++;
                tempCalObj.add(tempCalObj.DAY_OF_MONTH, 1);
        }
    }

    private void GenerateYear() {
        pnlCenterCenterYear.removeAll();
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH; 
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        
        int columns = 3;                // number of columns for month layout
        int rows = 4;                   // number of rows for month layout
        final int numOfDaysInWeek = 7;                              // Max number of days each week can have
        final int maxMonthSlots = numOfDaysInWeek*numOfDaysInWeek;  // Max number of days each month can possible have

        // loop to output each day of the month to the calendar
        for ( int y = 0; y < columns; y++ )      // columns
        {                
            for(int x = 0; x < rows; x++)       // rows
            {
                Calendar tempCalObj = Calendar.getInstance();
                // sets tempCalObj to the year, month, and lowest date of the current month
                tempCalObj.set(curDate.get(curDate.YEAR),(((y)*rows + x)), curDate.getActualMinimum(curDate.DAY_OF_MONTH));
                
                // creates a JPanel for the current month
                JPanel monthPanel = new JPanel(new BorderLayout());
                JPanel monthPanelNorth = new JPanel(new FlowLayout());
                JPanel monthPanelCenter = new JPanel(yearLayout);
                
                monthPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));        
                
                int firstDayCount = 0; // used to determine when to output the day of the week
                int cellsAdded = 0; // used to add cells to the end of the calendar
                // creates a new Calendar object
                
                
                JLabel monthName = new JLabel("" + tempCalObj.getDisplayName(tempCalObj.MONTH, Calendar.LONG, Locale.ENGLISH));
                monthPanelNorth.add(monthName);
                // loop to output each day of the month to the calendar
                for ( boolean firstRun = true; cellsAdded < maxMonthSlots; firstRun = false)
                {                            
                        // does the following if this is the first run...
                        while ( tempCalObj.get(tempCalObj.DAY_OF_WEEK) != Calendar.SUNDAY && firstRun )
                        {
                            tempCalObj.add(tempCalObj.DAY_OF_MONTH, -1);
                        }
                        // creates a JPanel for the current day
                        JPanel dayPanel = new JPanel();
                        if ( tempCalObj.get(tempCalObj.MONTH) != tempCalObj.get(((y)*rows + x)))
                        {
                        }
                        if ( tempCalObj.get(Calendar.DAY_OF_MONTH) == curDate.get(Calendar.DAY_OF_MONTH) && tempCalObj.get(Calendar.MONTH) == curDate.get(Calendar.MONTH) && tempCalObj.get(Calendar.YEAR) == curDate.get(Calendar.YEAR) )
                        {
                            dayPanel.setBackground(Color.CYAN);
                            //dayPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

                        }
                        else
                        {
                            dayPanel.setBackground(Color.WHITE);
                            //dayPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                        }
                        if ( firstDayCount < numOfDaysInWeek )
                        {
                            // sets the panel to a BoxLayout
                            dayPanel.setLayout(new GridLayout(1,1)); //new BoxLayout(dayPanel, BoxLayout.Y_AXIS));
                            // outputs the day number at the bottom of the cell
                            JLabel lblYearViewDayofWeek = new JLabel(DAYS_OF_WEEK[firstDayCount].substring(0, 2));
                            lblYearViewDayofWeek.setHorizontalAlignment(JLabel.CENTER);
                            lblYearViewDayofWeek.setVerticalAlignment(JLabel.CENTER);
                            dayPanel.add(lblYearViewDayofWeek);
                            // adds the cell
                            
                            constraints.gridx = (cellsAdded)%(numOfDaysInWeek);                             // starting row for label
                            constraints.gridy = (cellsAdded-(cellsAdded%numOfDaysInWeek))/numOfDaysInWeek;  // starting column for label
                            constraints.gridwidth = 1;                                                      // how many columns it goes accross
                            constraints.gridheight = 1;                                                     // how many rows it goes accross
                            yearLayout.setConstraints(dayPanel, constraints);
                            monthPanelCenter.add(dayPanel);
                            
                            firstDayCount++;
                        }
                        else
                        {
                            if(cellsAdded<(2*numOfDaysInWeek) && tempCalObj.get(tempCalObj.DAY_OF_MONTH)>numOfDaysInWeek)
                            {
                                
                            }
                            else if(cellsAdded>maxMonthSlots-(2*numOfDaysInWeek) && tempCalObj.get(tempCalObj.DAY_OF_MONTH)<numOfDaysInWeek)
                            {
                                
                            }
                            else
                            {
                                constraints.gridx = (cellsAdded)%(numOfDaysInWeek);                             // starting row for label
                                constraints.gridy = (cellsAdded-(cellsAdded%numOfDaysInWeek))/numOfDaysInWeek;  // starting column for label
                                constraints.gridwidth = 1;                                                      // how many columns it goes accross
                                constraints.gridheight = 1;                                                     // how many rows it goes accross
                                yearLayout.setConstraints(dayPanel, constraints);
                                // sets the panel to a BoxLayout
                                dayPanel.setLayout(new GridLayout(1,1)); //new BoxLayout(dayPanel, BoxLayout.Y_AXIS));
                                // outputs the day number at the bottom of the cell
                                JLabel lblYearViewDay = new JLabel(tempCalObj.get(tempCalObj.DAY_OF_MONTH) + "");
                                lblYearViewDay.setHorizontalAlignment(JLabel.CENTER);
                                lblYearViewDay.setVerticalAlignment(JLabel.CENTER);
                                dayPanel.add(lblYearViewDay);
                                
                                // adds the cell
                                monthPanelCenter.add(dayPanel);
                            }
                            
                            tempCalObj.add(tempCalObj.DAY_OF_MONTH, 1);
                        }
                        cellsAdded++;
                        
                        // checks to see if that month needs an extra week
                        if(cellsAdded==maxMonthSlots-numOfDaysInWeek && tempCalObj.get(tempCalObj.DAY_OF_MONTH)<numOfDaysInWeek)
                        {
                            cellsAdded = maxMonthSlots;
                        }                            
                }
                
                // adds the months to the month panel
                monthPanel.add(monthPanelNorth, BorderLayout.NORTH);
                monthPanel.add(monthPanelCenter, BorderLayout.CENTER);
                
                // adds the month panel to the gui
                constraints.gridx = x;                   // define constraints for panel
                constraints.gridy = y;
                constraints.gridwidth = 1;
                constraints.gridheight = 1; 
                yearLayout.setConstraints(monthPanel, constraints);            
                pnlCenterCenterYear.add(monthPanel);
            }
        }
    }

    private boolean DisplayEvent(Event checkEvent) {
        for ( JCal tempCal : sampleUser.getUserCalendars() )
        {
            for ( EventGroup tempEventGroup : tempCal.getEventGroup() )
            {
                for ( Event tempEvent : tempEventGroup.getEvent() )
                {
                    if ( tempEvent == checkEvent )
                    {
                        if ( tempEventGroup.isCbxIsSelected() && tempCal.isCbxIsSelected() )
                        {
                            return true;
                        }
                        else
                        {
                            return false;
                        }
                    }
                }
            }
        }
        return false;
    }

    private int FindJCal(Event findEvent) {
        int i = 0;
        for ( JCal tempCal : sampleUser.getUserCalendars() )
        {
            for ( Event tempEvent : tempCal.getAllEvents() )
            {
                if ( tempEvent == findEvent )
                {
                    return i;
                }
            }
            i++;
        }
        return i;
    }

    private int FindEventGroup(Event findEvent, int jCalIndex) {
        JCal tempCal = sampleUser.getUserCalendars().get(jCalIndex);
        int i = 0;
        for ( EventGroup tempEventGroup : tempCal.getEventGroup() )
        {
            for ( Event tempEvent : tempEventGroup.getEvent() )
            {
                if ( tempEvent == findEvent )
                {
                    return i;
                }
            }
            i++;
        }
        return i;
    }

    private void DeleteEvent(Event deleteEvent) {
        for ( JCal tempCals : sampleUser.getUserCalendars() )
        {
            for ( EventGroup tempEventGroup : tempCals.getEventGroup() )
            {
                for ( int i = 0; i < tempEventGroup.getEvent().size(); i++ )
                {
                    if ( tempEventGroup.getEvent().get(i) == deleteEvent )
                    {
                        tempEventGroup.removeEvent(i+1);
                    }
                }
            }
        }
    }

    private void GenerateViews() {
        GenerateDay();
        GenerateWeek();
        GenerateMWeek();
        GenerateMonth();
        GenerateYear();
        validate();
        repaint();
    }
}
