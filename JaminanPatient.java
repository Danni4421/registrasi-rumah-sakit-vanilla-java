package FINAL;

public class JaminanPatient {
  public static void checkSaldoPatient(String[][] dataPatient, String[][] bookingData, double saldoPatient[][]) {
    System.out.println("<<<<< JAMINAN PASIEN >>>>>");
    System.out.printf("Nama Pasien\t: %s\n", dataPatient[App.getDataLogin][0]);
    System.out.printf("Jaminan Pasien \t: %s\n", bookingData[App.getDataLogin][3]);
    if (bookingData[App.getDataLogin][4].equals("-")) {
      System.out.println("Tidak memiliki Jaminan");
    } else {
      System.out.printf("Saldo Jaminan Pasien : %.3f", saldoPatient[0][App.getDataLogin]);
    }
  }

  public static void changeJaminan(String[][] dataPatient, String[][] bookingData, String[][] string2dInitialized) {
    System.out.println("<<<<< JAMINAN PASIEN >>>>>");
    System.out.println("Jaminan Anda Sekarang : " + bookingData[App.getDataLogin][3]);

    System.out.println("Apakah Anda ingin mengganti Jaminan? [y/n] : ");
    char getInput = App.sc.next().charAt(0);
    if (getInput == 'y') {
      for (int i = 0; i < string2dInitialized[0].length; i++) {
        System.out.printf("[ %d. %s ]", (i + 1), string2dInitialized[0][i]);
      }
      App.Enter(1);
      int getNewJaminan = App.sc.nextInt();
      bookingData[App.getDataLogin][3] = string2dInitialized[0][getNewJaminan - 1];
    }
  }

  public static double addSaldo(double[][] saldoJaminanNBillPatient) {
    System.out.println("<<<<< JAMINAN PASIEN >>>>>");
    System.out.println("Saldo User Sekarang : " + saldoJaminanNBillPatient[0][App.getDataLogin]);
    App.Enter(1);
    System.out.print("Apakah kamu ingin menambah saldo? ");
    char getInput = App.sc.next().charAt(0);
    if (getInput == 'y') {
      System.out.print("Masukkan Nilai => ");
      saldoJaminanNBillPatient[0][App.getDataLogin] += App.sc.nextDouble();
    }

    return saldoJaminanNBillPatient[0][App.getDataLogin];
  }

  public static void updateBillPatient(double saldoJaminanNew, String[][] bookingData,
      double[][] saldoJaminanNBillPatient, boolean[][] bookingNPaidDone) {
    double getOldBill = saldoJaminanNBillPatient[1][App.getDataLogin];
    double newResult = getOldBill - saldoJaminanNew;
    double sisaSaldo = saldoJaminanNew - getOldBill;

    if (newResult <= 0) {
      bookingData[App.getDataLogin][7] = "Lunas";
      bookingNPaidDone[1][App.getDataLogin] = true;
      saldoJaminanNBillPatient[1][App.getDataLogin] = 0;
    } else {
      bookingData[App.getDataLogin][7] = Double.toString(newResult);
      saldoJaminanNBillPatient[1][App.getDataLogin] = newResult;
    }

    if (sisaSaldo < 0) {
      sisaSaldo = 0;
    }

    saldoJaminanNBillPatient[0][App.getDataLogin] = sisaSaldo;
  }

}
