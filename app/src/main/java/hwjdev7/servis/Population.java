package hwjdev7.servis;

import hwjdev7.storage.DatabasePopulateService;

import java.time.LocalDate;

public class Population {
    public void populationInit(){
        boolean flag;
        String[] nameClient={"Ramesh","Bogdan","Bill","Liliya","Vlad"};
        int[] project_id ={1,1,1,1,1,2,2,2,3,3,3,4,4,4,4,5,5,5,6,6,7,8,9,9,11,11,11,11,11,11};
        int[]  worker_id={1,2,3,4,6,7,8,9,1,2,3,1,2,3,8,11,3,2,1,3,9,3,3,6,7,1,5,4,3,11};
        //
        String[][] worker ={ {"Max",  "2000-01-01", "Junior" ,"1500"},
                { "Oleg", "2001-01-17", "Trainee", "700"},
                { "Artur", "1992-03-02",  "Middle", "5000"},
                {"Anna", "1994-04-03", "Junior" ,"2000"},
                {"Tetiana", "1995-05-04", "Trainee", "800"},
                {"Roman", "1985-06-06", "Senior", "20000"},
                {"Sasha", "1990-06-07",  "Middle","4000"},
                { "Kira", "2002-03-04", "Trainee", "900"},
                {"Nata", "1986-07-08", "Junior" ,"1400"},
                {"Lina", "1989-01-31", "Junior" ,"1900"},
                { "Leo", "1999-12-11", "Senior", "20000"},
                { "Lana", "1990-01-31", "Junior" ,"1900"}};
//
        String [][]  project={{ "1", "2023-07-01","2023-12-01"},
                {"1", "2023-04-01", "2023-11-01"},
                {"5", "2023-01-01", "2025-08-01"},
                {"4", "2023-03-01", "2023-09-01"},
                { "4", "2023-06-01", "2024-08-01"},
                { "3", "2023-02-01", "2024-12-01"},
                { "4", "2022-03-01", "2023-08-01"},
                { "4", "2022-06-01", "2023-10-01"},
                { "4", "2023-04-01", "2028-08-01"},
                { "3","2023-07-01", "2023-08-01"},
                { "1", "2015-01-01", "2023-05-01"}};

        for (int i=0; i<5;i++) {
            flag = DatabasePopulateService.createClient(nameClient[i]);
            if(!flag){
                System.out.println(" Помилка! createClient = "+nameClient[i]);
            }
        }
        for (int i=0; i<30;i++) {
            flag = DatabasePopulateService.createProjectWorker(project_id[i],worker_id[i]);
            if(!flag){
                System.out.println(" Помилка! createProjectWorker = "+project_id[i]+"  "+worker_id[i]);
            }
        }
        for (int i=0; i<11;i++) {
            int j = 0;
            flag = DatabasePopulateService.createProject(Integer.parseInt(project[i][j]), LocalDate.parse(project[i][j + 1]), LocalDate.parse(project[i][j + 2]));
            if (!flag) {
                System.out.println(" Помилка! createProject = " + project[i][j] + "  " + project[i][j + 1] + "  " + project[i][j + 2]);
            }
        }

        for (int i=0; i<12;i++) {
            int j = 0;
            flag = DatabasePopulateService.createNewWorker(worker[i][j], LocalDate.parse(worker[i][j + 1]), worker[i][j + 2], Integer.parseInt(worker [i][j + 3]));
            if (!flag) {
                System.out.println(" Помилка!createNewWorker = " + worker [i][j] + "  " + worker [i][j+1] + "  " + worker [i][j+2]+" "+ worker [i][j+3]);
            }

        }

    }
}
