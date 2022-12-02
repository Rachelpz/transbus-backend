package cu.edu.cujae.backend.service;

import cu.edu.cujae.backend.core.dto.GroupDto;
import cu.edu.cujae.backend.core.service.GroupService;
import cu.edu.cujae.backend.core.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {
    private static GroupServiceImpl groupservices = null;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private CountryService countryService;

    @Override
    public void createGroup(GroupDto group) throws SQLException {
        try(Connection conn = jdbcTemplate.getDataSource().getConnection()){
            CallableStatement CS = conn.prepareCall(
                    "{call touristic_group_insert(?,?,?)}");

            CS.setString(1, group.getGroup_name());
            CS.setInt(2, group.getPaxamount());
            CS.setInt(3, group.getCountry().getCountry_id());

            CS.executeUpdate();
        }
    }

    @Override
    public void updateGroup(GroupDto group) throws SQLException {
        try(Connection conn = jdbcTemplate.getDataSource().getConnection()){
            CallableStatement CS = conn.prepareCall(
                    "{call touristic_group_update(?,?,?,?)}");

            CS.setInt(1, group.getGroup_id());
            CS.setString(2, group.getGroup_name());
            CS.setInt(3, group.getPaxamount());
            CS.setInt(4, group.getCountry().getCountry_id());

            CS.executeUpdate();
        }

    }

    @Override
    public List<GroupDto> listGroups() throws SQLException {
        List<GroupDto> groupList = new ArrayList<GroupDto>();

        try(Connection conn = jdbcTemplate.getDataSource().getConnection()){
            ResultSet rs = conn.createStatement().executeQuery(
                    "SELECT * FROM touristic_group join country on country.country_id=touristic_group.country_id order by country_name");

            while(rs.next()){
                GroupDto group = new GroupDto(
                        rs.getInt("group_id"),
                        rs.getString("group_name"),
                        rs.getInt("paxamount"),
                        countryService.getCountryById(rs.getInt("country_id"))
                );
                groupList.add(group);
            }
        }

        return groupList;
    }

    @Override
    public GroupDto getGroupById(Integer groupId) throws SQLException {
        GroupDto group = null;

        try(Connection conn = jdbcTemplate.getDataSource().getConnection()){
            PreparedStatement pstmt = conn.prepareStatement(
                    "SELECT * FROM touristic_group join country on country.country_id=touristic_group.country_id where group_id=?");

            pstmt.setInt(1, groupId);

            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){
                group = new GroupDto(
                        rs.getInt("group_id"),
                        rs.getString("group_name"),
                        rs.getInt("paxamount"),
                        countryService.getCountryById(rs.getInt("country_id"))
                );
            }
        }

        return group;
    }

    @Override
    public void deleteGroup(Integer id) throws SQLException {
        try(Connection conn = jdbcTemplate.getDataSource().getConnection()){
            CallableStatement CS = conn.prepareCall(
                    "{call touristic_group_delete(?)}");

            CS.setInt(1, id);
            CS.executeUpdate();
        }
    }
}
