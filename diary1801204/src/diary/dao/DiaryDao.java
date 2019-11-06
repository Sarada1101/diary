
package diary.dao;

import diary.bean.DiaryBeans;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * データベースから日誌の情報を取得、更新、削除を行うクラス
 *
 * @author ryouta
 */
public class DiaryDao extends DaoBase {

    /**
     * ログインしている人の日誌の情報をすべて取得(SELECT)してリストにして返す
     *
     * @param student_id ログインしている学生の学籍番号
     * @return 取得した日誌の情報を格納したリスト
     */
    public List<DiaryBeans> fetchDiaryListFromDb(String student_id) {
        //test
        System.out.println("DiaryDao : fetchDiaryListFromDb");
        System.out.println("param : student_id = " + student_id);

        DiaryBeans diary_beans = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<DiaryBeans> list = null;

        try {
            this.dbConnect();
            stmt = con.prepareStatement("SELECT * FROM diary WHERE student_id = ? ORDER BY insert_date DESC");
            stmt.setString(1, student_id);
            rs = stmt.executeQuery();

            list = new ArrayList<>();

            while (rs.next()) {
                diary_beans = new DiaryBeans();
                diary_beans.setClass_code(rs.getString("class_code"));
                diary_beans.setInsert_date(rs.getString("insert_date"));
                diary_beans.setStudent_id(rs.getString("student_id"));
                diary_beans.setGood_point(rs.getString("good_point"));
                diary_beans.setBad_point(rs.getString("bad_point"));
                diary_beans.setStudent_comment(rs.getString("student_comment"));
                diary_beans.setTeacher_comment(rs.getString("teacher_comment"));
                list.add(diary_beans);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                this.dbClose();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("return : list = " + list); //test
        return list;
    }

    /**
     * データベースに学生が入力した日誌の情報を登録(INSERT)する
     *
     * @param diary_beans データベースに登録する日誌の情報
     * @return 日誌の登録に成功したらtrue
     */
    public boolean insertDiaryToDb(DiaryBeans diary_beans) {
        //test
        System.out.println("DiaryDao : insertDiaryToDb");
        System.out.println("param : diaryBeans = " + diary_beans);
        System.out.println("                   : class_code = " + diary_beans.getClass_code());
        System.out.println("                   : insert_date = " + diary_beans.getInsert_date());
        System.out.println("                   : student_id = " + diary_beans.getStudent_id());
        System.out.println("                   : good_point = " + diary_beans.getGood_point());
        System.out.println("                   : bad_point = " + diary_beans.getBad_point());
        System.out.println("                   : studen_comment = " + diary_beans.getStudent_comment());

        PreparedStatement stmt = null;

        try {
            this.dbConnect();
            stmt = con.prepareStatement("INSERT INTO diary (class_code, insert_date, student_id, good_point, bad_point, student_comment) VALUES (?, ?, ?, ?, ?, ?)");
            stmt.setString(1, diary_beans.getClass_code());
            stmt.setString(2, diary_beans.getInsert_date());
            stmt.setString(3, diary_beans.getStudent_id());
            stmt.setString(4, diary_beans.getGood_point());
            stmt.setString(5, diary_beans.getBad_point());
            stmt.setString(6, diary_beans.getStudent_comment());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("return : is_success = false"); //test
            return /* is_success = */ false;

        } finally {
            try {
                this.dbClose();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("return : is_success = true"); //test
        return /* is_success = */ true;
    }

    /**
     * データベースから学生が指定した日誌の情報を削除(DELETE)する
     *
     * @param diary_beans データベースから削除する日誌の情報
     */
    public void deleteDiaryFromDb(DiaryBeans diary_beans) {
        //test
        System.out.println("DiaryDao : deleteDiaryFromDb");
        System.out.println("param : diaryBeans = " + diary_beans);
        System.out.println("                   : class_code = " + diary_beans.getClass_code());
        System.out.println("                   : insert_date = " + diary_beans.getInsert_date());

        PreparedStatement stmt = null;

        try {
            this.dbConnect();
            stmt = con.prepareStatement("DELETE FROM diary WHERE class_code = ? AND insert_date = ?");
            stmt.setString(1, diary_beans.getClass_code());
            stmt.setString(2, diary_beans.getInsert_date());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                this.dbClose();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * データベースの情報を学生が入力した情報に更新(UPDATE)する
     *
     * @param diary_beans 更新する日誌の情報
     */
    public void updateDiaryToDb(DiaryBeans diary_beans) {
        //test
        System.out.println("DiaryDao : updateDiaryToDb");
        System.out.println("param : diaryBeans = " + diary_beans);
        System.out.println("                   : class_code = " + diary_beans.getClass_code());
        System.out.println("                   : insert_date = " + diary_beans.getInsert_date());
        System.out.println("                   : student_id = " + diary_beans.getStudent_id());
        System.out.println("                   : good_point = " + diary_beans.getGood_point());
        System.out.println("                   : bad_point = " + diary_beans.getBad_point());
        System.out.println("                   : student_comment = " + diary_beans.getStudent_comment());

        PreparedStatement stmt = null;

        try {
            this.dbConnect();
            stmt = con.prepareStatement("UPDATE diary SET good_point = ?, bad_point = ?, student_comment = ? WHERE class_code = ? AND insert_date = ?");
            stmt.setString(1, diary_beans.getGood_point());
            stmt.setString(2, diary_beans.getBad_point());
            stmt.setString(3, diary_beans.getStudent_comment());
            stmt.setString(4, diary_beans.getClass_code());
            stmt.setString(5, diary_beans.getInsert_date());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                this.dbClose();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public List<DiaryBeans> fetchSortedDiaryListFromDb(String student_id, String sort_column, String sort_order) {
        //test
        System.out.println("DiaryDao : fetchSortedDiaryListFromDb");
        System.out.println("param : student_id = " + student_id);
        System.out.println("param : sort_column = " + sort_column);
        System.out.println("param : sort_order = " + sort_order);

        DiaryBeans diary_beans = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<DiaryBeans> list = null;

        try {
            this.dbConnect();
            stmt = con.prepareStatement("SELECT * FROM diary WHERE student_id = ? ORDER BY ? ?");
            stmt.setString(1, student_id);
            stmt.setString(2, sort_column);
            stmt.setString(3, sort_order);
            rs = stmt.executeQuery();

            list = new ArrayList<>();

            while (rs.next()) {
                diary_beans = new DiaryBeans();
                diary_beans.setClass_code(rs.getString("class_code"));
                diary_beans.setInsert_date(rs.getString("insert_date"));
                diary_beans.setStudent_id(rs.getString("student_id"));
                diary_beans.setGood_point(rs.getString("good_point"));
                diary_beans.setBad_point(rs.getString("bad_point"));
                diary_beans.setStudent_comment(rs.getString("student_comment"));
                diary_beans.setTeacher_comment(rs.getString("teacher_comment"));
                list.add(diary_beans);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                this.dbClose();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("return : list = " + list); //test
        return list;
    }
}
