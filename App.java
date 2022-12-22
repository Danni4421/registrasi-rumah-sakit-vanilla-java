package FINAL;

import java.util.Scanner;
import java.util.Random;

/**
 * App
 */
public class App {
  public static Scanner sc = new Scanner(System.in);
  public static Random randPick = new Random();

  public static int getDataLogin = 0;
  public static int takeInputPoli = 0;
  public static int userIndexAcc = 0;
  public static int paymentIndex = 0;
  public static boolean login = false;
  // Array STRING
  static String dataPatient[][] = new String[100][6];
  static String account[][] = new String[dataPatient.length][2];
  static String bookingData[][] = new String[dataPatient.length][10];
  static String paymentCode[] = new String[dataPatient.length];
  static String infoBookingUser1[] = new String[dataPatient.length];
  static int kuotaDokterUmum[] = {
      10, 10, 10, 10, 10, 10, 10
  };
  static int nomorKuotaDokterUmum[] = new int[kuotaDokterUmum.length];

  public static void main(String[] args) {

    String[][] String2dInitialized = {
        { "Jamkesda", "BPJS", "Pribadi", "Asuransi", "None" },
        {
            "Kulit Dan Kelamin",
            "Anak",
            "Bedah",
            "Fisioterapi",
            "Gigi",
            "Jantung",
            "Mata",
            "Paru",
            "Penyakit Dalam"
        },
        {
            "Agus Santoso",
            "Supriyatna Cahyani",
            "Idang Suparman",
            "Hanif Mochsin Frimawan",
            "Eja Rahardian",
            "Parulian Jaya",
            "Niko Sukmawati"
        }
    };
    String[][][] String3dInitialized = {
        {
            { "Putri Rachma Safitri, dr., Sp.DV" },
            { "Erwin Sutejo, dr., SP.A", "Esthy Poespitaningtyas, dr., Sp.A" },
            { "Agung Kusumanegara, dr., Sp.B", "Budi S, dr., Sp.B" },
            { "Berkah Rivaldi Kusuma, Amd.Kes" },
            { "Maya Puspitasari, drg." },
            { "Andrianus Oktovianto, dr., Sp.JP" },
            { "Teguh, dr., Sp.M" },
            { "Vinodini Merinda, dr., Sp.P" },
            { "Hesti, DR, SP.PD", "Purwakaning Purnomo Agung, dr.Sp PD" }
        },
        {
            { "08:00" },
            { "09:00", "14:00" },
            { "09:00", "14:00" },
            { "08:30" },
            { "09:00" },
            { "10:00" },
            { "07:30" },
            { "14:30" },
            { "10:00", "16:00" }
        },
        {
            { "BNI", "BRI", "Mandiri", "BCA" },
            { "BNI", "BRI", "Mandiri", "BCA" },
            { "Dana", "OVO", "LinkAja" },
            { "Indomaret", "Alfamart" }
        }
    };

    // Array BOOLEAN
    boolean bookingNPaidDone[][] = new boolean[2][dataPatient.length];
    boolean jadwalDokter[][] = new boolean[String3dInitialized[0].length][2];

    // Array INT
    int kuotaDokter[][] = {
        { 1 },
        { 5, 6 },
        { 4, 6 },
        { 5 },
        { 7 },
        { 5 },
        { 4 },
        { 6 },
        { 5, 7 }
    };

    int nomorKuota[][] = new int[String2dInitialized[1].length][2];
    int array2dDate[][] = new int[dataPatient.length][3];
    int infoBookingUser2[] = new int[dataPatient.length];

    // Array DOUBLE
    double biayaAwal[] = {
        100.000, 80.000, 90.000, 150.000, 70.000, 200.000, 150.000, 300.000, 250.000
    };
    // double saldoJaminanUser[] = new double[dataPatient.length];
    // double billPatient[] = new double[dataPatient.length];
    double[][] saldoJaminanNBillPatient = new double[2][dataPatient.length];

    // Array CHAR
    char[][] randomCodePreparation = {
        {
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'I', 'J', 'K', 'L', 'M', 'N',
            'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
        },
        {
            '1', '2', '3', '4', '5', '6', '7', '8', '9'
        },

    };
    // Type Data Boolean Variable
    boolean conditionOfProgram = false;

    // ASCII LOGO
    System.out.println();
    System.out.println(
        "██████╗ ███████╗    ███████╗ █████╗ ████████╗██╗  ██╗███╗   ███╗ █████╗     ███╗   ███╗███████╗██████╗ ██╗██╗  ██╗ █████╗ ");
    System.out.println(
        "██╔══██╗██╔════╝    ██╔════╝██╔══██╗╚══██╔══╝██║  ██║████╗ ████║██╔══██╗    ████╗ ████║██╔════╝██╔══██╗██║██║ ██╔╝██╔══██╗");
    System.out.println(
        "██████╔╝███████╗    █████╗  ███████║   ██║   ███████║██╔████╔██║███████║    ██╔████╔██║█████╗  ██║  ██║██║█████╔╝ ███████║");
    System.out.println(
        "██╔══██╗╚════██║    ██╔══╝  ██╔══██║   ██║   ██╔══██║██║╚██╔╝██║██╔══██║    ██║╚██╔╝██║██╔══╝  ██║  ██║██║██╔═██╗ ██╔══██║");
    System.out.println(
        "██║  ██║███████║    ██║     ██║  ██║   ██║   ██║  ██║██║ ╚═╝ ██║██║  ██║    ██║ ╚═╝ ██║███████╗██████╔╝██║██║  ██╗██║  ██║");
    System.out.println(
        "╚═╝  ╚═╝╚══════╝    ╚═╝     ╚═╝  ╚═╝   ╚═╝   ╚═╝  ╚═╝╚═╝     ╚═╝╚═╝  ╚═╝    ╚═╝     ╚═╝╚══════╝╚═════╝ ╚═╝╚═╝  ╚═╝╚═╝  ╚═╝");

    // Main Program Start
    startFrom1(nomorKuota, kuotaDokter, nomorKuotaDokterUmum, kuotaDokterUmum);
    do {
      menu(account, dataPatient, bookingData, jadwalDokter, kuotaDokter, biayaAwal, bookingNPaidDone,
          randomCodePreparation, paymentCode, String2dInitialized, String3dInitialized, saldoJaminanNBillPatient,
          array2dDate, infoBookingUser1, infoBookingUser2, kuotaDokterUmum, nomorKuota);
      conditionOfProgram = getConfirmation("Ke Menu");
    } while (conditionOfProgram);
  }

  private static void startFrom1(int[][] nomorKuota, int[][] kuotaDokter, int[] nomorKuotaDokterUmum,
      int[] kuotaDokterUmum) {
    for (int i = 0; i < kuotaDokter.length; i++) {
      for (int j = 0; j < kuotaDokter[i].length; j++) {
        nomorKuota[i][j] = 1;
      }
    }

    for (int i = 0; i < kuotaDokterUmum.length; i++) {
      nomorKuotaDokterUmum[i] = 1;
    }
  }

  public static void menu(String[][] account, String[][] dataPatient, String[][] bookingData, boolean[][] jadwalDokter,
      int[][] kuotaDokter, double[] biayaAwal, boolean[][] bookingNPaidDone, char[][] randomCodePreparation,
      String[] paymentCode, String[][] string2dInitialized, String[][][] string3dInitialized,
      double[][] saldoJaminanNBillPatient, int[][] array2dDate, String[] infoBookingUser1, int[] infoBookingUser2,
      int[] kuotaDokterUmum, int[][] nomorKuota) {
    Enter(1);
    // menuToogle("MENU");
    System.out.println("=============================================");
    System.out.println("||                                         ||");
    System.out.println("||                MENU AWAL                ||");
    System.out.println("||                                         ||");
    System.out.println("||__ Pelayanan Rumah Sakit Fathma Medika __||");
    System.out.println();
    System.out.println("=============================================");
    System.out.println("||    1. LOGIN                             ||");
    System.out.println("=============================================");
    System.out.println("||    2. REGISTRASI                        ||");
    System.out.println("=============================================");
    System.out.println("||    3. CHECK JADWAL                      ||");
    System.out.println("=============================================");
    System.out.print("=> ");
    int getInputMenu = sc.nextInt();

    switch (getInputMenu) {
      case 1:
        int getMenuLogin = menuLogin();
        switch (getMenuLogin) {
          case 1:
            login = MethodLogin.checkLogin(account);
            if (login) {
              System.out.println("Anda berhasil Login");
            }

            while (login) {
              MethodLogin.LoginTrue(account, dataPatient, bookingData, jadwalDokter, kuotaDokter, biayaAwal,
                  randomCodePreparation, bookingNPaidDone, paymentCode, string2dInitialized, string3dInitialized,
                  saldoJaminanNBillPatient, array2dDate, infoBookingUser1, infoBookingUser2, kuotaDokterUmum,
                  nomorKuota);
            }
            break;

          case 2:
            AdminPage.checkResult(account, bookingData, dataPatient, infoBookingUser1, infoBookingUser2,
                string2dInitialized, string3dInitialized, kuotaDokter);
            break;
          case 3:
            System.out.println("Anda Berhasil Kembali");
            break;
        }
        break;

      case 2:
        MethodRegister.Register(dataPatient, account);
        break;

      case 3:
        getJadwalDoktor(jadwalDokter, string3dInitialized, string2dInitialized, kuotaDokter);
        break;
    }

  }

  private static int menuLogin() {
    Enter(1);
    System.out.println("=================================================");
    System.out.println("||                 HALAMAN LOGIN               ||");
    System.out.println("============ Rumah Sakit Fathma Medika ==========");
    System.out.println("-------------------------------------------------");
    System.out.println();
    System.out.println("1. Pasien");
    System.out.println("2. Admin");
    System.out.println("3. Kembali");
    System.out.print("=> ");
    int getDataLogin = sc.nextInt();

    return getDataLogin;
  }

  public static void getJadwalDoktor(boolean[][] jadwalDokter, String[][][] string3dInitialized,
      String[][] string2dInitialized, int[][] kuotaDokter) {
    Enter(1);
    System.out.println("===================================================");
    System.out.println("||               JADWAL POLIKLINIK               ||");
    System.out.println("============ Rumah Sakit Fathma Medika ============");
    System.out.println();
    for (int i = 0; i < string2dInitialized[1].length; i++) {
      System.out.println("[" + string2dInitialized[1][i] + "] : ");
      System.out.println("===================================================");
      for (int j = 0; j < string3dInitialized[0][i].length; j++) {
        System.out.println("Dokter : " + string3dInitialized[0][i][j]);
        if (jadwalDokter[i][j]) {
          System.out.println("Kuota Penuh");
          System.out.printf("Jadwal Dokter : %s  Kuota : %d\n", string3dInitialized[1][i][j], kuotaDokter[i][j]);
          System.out.println("===================================================");
        } else {
          System.out.println("Kuota Masih Tersedia!! ^_^");
          System.out.printf("Jadwal Dokter : %s  Kuota : %d\n", string3dInitialized[1][i][j], kuotaDokter[i][j]);
          System.out.println("===================================================");
        }
      }
      System.out.println("\n");
    }
  }

  public static void menuToogle(String menuInput) {
    System.out.println("=============================");
    System.out.printf("\t%s\n", menuInput);
    System.out.println("=============================");
  }

  public static boolean showPoli(String[] poli, int indexPelayanan) {
    boolean result = false;
    if (indexPelayanan > 1) {
      for (int i = 1; i <= poli.length; i++) {
        System.out.printf("%d. %s \n", i, poli[i - 1]);
        result = true;
      }
    } else {
      System.out.println("Anda masuk Poli Umum");
      result = false;
    }

    return result;
  }

  public static boolean getConfirmation(String pesanTambahan) {
    Enter(1);
    boolean resultInput = false;
    System.out.printf("Ingin Kembali %s [y/n] : ", pesanTambahan);
    char input = sc.next().charAt(0);
    if (input == 'y') {
      resultInput = true;
    } else if (input == 'n') {
      resultInput = false;
    } else {
      resultInput = false;
    }

    return resultInput;
  }

  public static void Enter(int k) {
    for (int i = 0; i < k; i++) {
      System.out.println();
    }
  }

  public static String getRandomCode(char[][] randomCodePreparation, int i) {

    String finalResult = "";

    if (i == 0) {
      return "";
    } else {
      int getRandPick = randPick.nextInt(2);
      if (getRandPick == 0) {
        int getRandIndex = randPick.nextInt(25);
        finalResult += randomCodePreparation[0][getRandIndex] + getRandomCode(randomCodePreparation, i - 1);
      } else if (getRandPick == 1) {
        int getRandIndex = randPick.nextInt(9);
        finalResult += randomCodePreparation[1][getRandIndex] + getRandomCode(randomCodePreparation, i - 1);
      }

      return finalResult;
    }

  }

}
