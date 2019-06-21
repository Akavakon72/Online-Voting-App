/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.btv.conn;

import com.bean.ResultBean;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.connection.data.services.DatabaseController;

public class ConnectionClass {

    private DatabaseController controller;
    Date date = new Date();
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    public ConnectionClass() {

        controller = new DatabaseController("localhost", "3306", "voting", "root", "");
//        System.out.println(dateFormat.format(date)); //2014/08/06 15:59:48
    }

    public String vote(String username, String vgroup, String que, String ans) {
        String stat = "FALSE";
        try {

            StringBuffer qry = new StringBuffer("select * from vote where uname='" + username + "' AND dept='" + vgroup + "' AND "
                    + "post='" + que + "'");
            ResultSet executeQuery = controller.executeQuery(qry);

            if (executeQuery.next()) {
                stat = "FALSE:";
                return stat;
            } else {
                StringBuffer str = new StringBuffer("INSERT INTO vote(uname,dept,post,opt,dnt) values('" + username + "','"
                        + vgroup.toString() + "','" + que.toString() + "','" + ans.toString() + "','" + dateFormat.format(date).toString() + "')");
                controller.execute(str);
                stat = "TRUE";
            }
        } catch (Exception l) {
            l.printStackTrace();
            stat = "FALSE:";
        }
        return (stat);
    }

    public String addCandidate(String dep, String post, String opt1, String opt2, String opt3) {
        String stat = "FALSE";
        try {
            StringBuffer str = new StringBuffer("INSERT INTO candidates(dept,post,opt1,opt2,opt3)VALUES('"
                    + dep + "','" + post + "','" + opt1 + "','" + opt2 + "','" + opt3 + "')");
            controller.execute(str);
            stat = "TRUE";
        } catch (Exception l) {
            stat = "FALSE:";
        }
        return (stat);
    }

    public String deleteCandidate(String post) {
        String stat = "FALSE";
        try {
            StringBuffer str = new StringBuffer("DELETE FROM candidates WHERE post='" + post + "'");
            controller.execute(str);
            stat = "TRUE";
        } catch (Exception e) {
            stat = "FALSE";
            e.printStackTrace();
        }

        return stat;
    }

    public String regVoter(String name, String uname, String email, String fathername,
            String mother, String aadhar, String votid, String Addr, String dob,
            String pass, String type, String status, String macadd, String question, String answer) {
        String stat = "FALSE";
        try {
            StringBuffer str2 = new StringBuffer("SELECT * FROM reg WHERE uname='" + uname + "' OR email='" + email + "'"
                    + " OR aadhar='" + aadhar + "' OR votid='" + votid + "'");
            ResultSet resultSet = controller.executeQuery(str2);

            if (resultSet.next()) {
                stat = "FALSE";

            } else {
                StringBuffer str = new StringBuffer("Insert into reg(name,uname,"
                        + "email,fathername,mothername,aadhar,"
                        + "votid,address,dob,pass,type,status,macadd,question,answer) Values('"
                        + name.toString() + "','" + uname + "','" + email
                        + "','" + fathername.toString() + "','" + mother + "','" + aadhar
                        + "','" + votid + "','" + Addr.toString() + "','" + dob + "','"
                        + pass + "','" + type + "','" + status + "','" + macadd
                        + "','" + question + "','" + answer + "')");
                controller.execute(str);
                stat = "TRUE";
            }
        } catch (Exception l) {
            l.printStackTrace();
            stat = "FALSE";
        } finally {
            controller.closeConnection();
        }
        return (stat);

    }

    public String login(String uname, String pass, String type) {
        String stat = "FALSE";
        try {
            StringBuffer chk = new StringBuffer("SELECT * FROM reg WHERE uname='" + uname + "' AND pass='" + pass + "' AND type='" + type + "'");
            if (controller.executeQuery(chk).next()) {
                StringBuffer str = new StringBuffer("UPDATE reg SET status='online' WHERE uname='" + uname + "' AND pass='" + pass + "' AND type='" + type + "'");
                boolean execute = controller.execute(str);
                stat = execute ? "TRUE" : "FALSE";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            stat = "FALSE";
        } finally {
            controller.closeConnection();
        }
        return (stat);
    }

    public String logOut(String uname, String pass, String type) {
        String stat = "FALSE";
        try {
            StringBuffer str = new StringBuffer("UPDATE reg SET status='offline' WHERE uname='" + uname + "' AND pass='" + pass + "' AND type='" + type + "'");
            controller.execute(str);
            stat = "TRUE";
        } catch (Exception l) {
            stat = "FALSE";
        } finally {
            controller.closeConnection();
        }
        return (stat);
    }

    public boolean addDepartment(String dept) {
        try {
            StringBuffer str = new StringBuffer("INSERT INTO dept(dept)VALUES('" + dept + "')");
            controller.execute(str);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            controller.closeConnection();
        }
    }

    public boolean deleteDepartment(String dept) {
        try {
            StringBuffer str = new StringBuffer("DELETE FROM dept WHERE dept='" + dept + "'");
            controller.execute(str);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            controller.closeConnection();
        }
    }

    public ResultSet getDepartments() {
        ResultSet rs = null;
        try {
            StringBuffer str = new StringBuffer("SELECT * FROM dept");
            rs = controller.executeQuery(str);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            controller.closeConnection();
        }
        return rs;
    }

    public ResultSet getPostData() {
        ResultSet rs = null;
        try {
            StringBuffer str = new StringBuffer("SELECT * FROM candidates");
            rs = controller.executeQuery(str);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            controller.closeConnection();
        }
        return rs;
    }

    public String addPost(String dept, String post, String candi1, String candi2, String candi3) {
        String response = "Failed";
        try {
            StringBuffer str1 = new StringBuffer("SELECT * FROM candidates WHERE dept='" + dept
                    + "' AND post='" + post + "'");
            ResultSet executeQuery = controller.executeQuery(str1);
            if (executeQuery.next()) {
                response = "Post Allready Assign For Department";
            } else {
                StringBuffer str3 = new StringBuffer("SELECT * FROM candidates "
                        + "WHERE opt1='" + candi1 + "' OR opt1='" + candi2 + "' OR opt1='" + candi3
                        + "' OR opt2='" + candi1 + "' OR opt2='" + candi2 + "' OR opt2='" + candi3
                        + "' OR opt3 ='" + candi1 + "' OR opt3='" + candi2 + "' OR opt3='" + candi3 + "'");
                ResultSet executeQuery3 = controller.executeQuery(str3);
                if (executeQuery3.next()) {
                    response = "Candidate/Candidates Allready Assign For Post";
                    System.out.println("=======");
                    return response;
                } else {
                    StringBuffer str = new StringBuffer("INSERT INTO candidates(dept,post,opt1,opt2,opt3 )VALUES('"
                            + dept + "','" + post + "','" + candi1 + "','" + candi2 + "','" + candi3 + "')");
                    System.out.println("QQ=" + str);
                    controller.execute(str);
                    response = "Post Addedd Successfully";
                }
            }
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            return response;
        } finally {
            controller.closeConnection();
        }
    }

    public ResultSet getPost(String dept) {
        ResultSet rs = null;
        try {
            StringBuffer str = new StringBuffer("SELECT * FROM candidates WHERE dept='" + dept + "'");
            rs = controller.executeQuery(str);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            controller.closeConnection();
        }
        return rs;
    }

    public boolean deletePost(String dept, String post) {
        try {
            StringBuffer str = new StringBuffer("DELETE FROM candidates WHERE dept='" + dept + "'AND post='" + post + "'");
            controller.execute(str);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            controller.closeConnection();
        }
    }

    public ResultSet getCandidates(String department) {
        //id,dept,post,opt1,op2,opt3
        String stat = "FALSE";
        ResultSet rs = null;
        try {
            StringBuffer str = new StringBuffer("SELECT * FROM candidates WHERE dept = '" + department + "'");
            rs = controller.executeQuery(str);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            controller.closeConnection();
        }
        return rs;
    }

    public String changePassword(String uname, String pass, String question, String answer) {
        String response = "FALSE";
        System.out.println("=====");
        try {
            Statement st = new ConnectionClass2().st;

            StringBuffer str = new StringBuffer("UPDATE reg set pass='" + pass + "'"
                    + " WHERE uname  ='" + uname + "' AND question='" + question + "' AND answer='" + answer + "'");

            System.out.println("QRY=" + str);

            int execute = st.executeUpdate(str.toString());

            System.out.println("res=" + execute);

            if (execute > 0) {
                response = "TRUE";
            } else {
                response = "FALSE";
            }
        } catch (Exception e) {
            e.printStackTrace();
            response = "FALSE";
        } finally {
//            controller.closeConnection();
        }
        return response;
    }

    public List<ResultBean> getVoteResult() {
        ResultSet rs = null;
        List<ResultBean> result = new ArrayList<>();
        List<ResultBean> list = new ArrayList<>();
        try {
//            StringBuffer str = new StringBuffer("SELECT dept,post,opt,count(opt) from vote group by opt order by dept");
            StringBuffer str = new StringBuffer("SELECT dept,post,opt,count(opt) from vote group by opt order by dept,post");
            rs = controller.executeQuery(str);
            Set<ResultBean> set = new HashSet<ResultBean>();

            while (rs.next()) {
                ResultBean bean = new ResultBean();
                System.out.println(" " + rs.getString(1) + "  " + rs.getString(2) + "  " + rs.getString(3) + "  " + rs.getString(4));
                bean.setDpt(rs.getString(1));
                bean.setPost(rs.getString(2));
                bean.setName(rs.getString(3));
                bean.setVote(rs.getInt(4));
                set.add(bean);
                list.add(bean);
            }
            for (ResultBean resultBean : set) {
                List<ResultBean> similarBeans = getSimilarBeans(resultBean, list);
                ResultBean max = Collections.max(similarBeans, new Comparator<ResultBean>() {
                    @Override
                    public int compare(ResultBean o1, ResultBean o2) {
                        return o1.getVote() > o2.getVote() ? 1 : -1;
                    }
                });
                System.out.println("max = " + max);
                result.add(max);
            }

            //<editor-fold defaultstate="collapsed" desc="old code">
//            for (String string : set) {
//                int max = 0;
//
//                String[] split = string.split(" ");
//
////                    max = 0;
//                for (ResultBean resultBean1 : list) {
//                    if (split[0].equals(resultBean1.getDpt())) {
//                        if (split[1].equals(resultBean1.getPost())) {
//                            int count = Integer.parseInt(resultBean1.getVote());
//                            if (max < count) {
//                                max = count;
//                            }
//                        }
//                    }
//
//                }
//                System.out.println("max+ " + max);
//
//            }
//</editor-fold>
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
//            controller.closeConnection();
        }
        return result;
    }

    private List<ResultBean> getSimilarBeans(ResultBean bean, List<ResultBean> beans) {
        List<ResultBean> list = new ArrayList<>();
        for (ResultBean resultBean : beans) {
            if (bean.equals(resultBean)) {
                list.add(resultBean);
            }
        }
        return list;
    }

    public static void main(String[] args) {
        ConnectionClass con = new ConnectionClass();
//        String regVoter = con.regVoter("", "", "saa", "", "", "", "", "asdsd", "asdsd", "", "", "", "");
//        System.out.println("" + regVoter);
        String changePassword = con.changePassword("s", "qw123eqwe", "a", "s");
        System.out.println("===>" + changePassword);
    }

}
