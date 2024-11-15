package com.itheima;

import com.itheima.mapper.DeptMapper;
import com.itheima.mapper.EmpMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//@SpringBootTest
class TliasWebApplicationTests {

    @Autowired
    private EmpMapper empMapper;
    private DeptMapper deptMapper;

    @Test
    void contextLoads() {
    }

    @Test
    public void testGenJwt(){
        Map<String, Object> claims = new HashMap<>();
        claims.put("id",1);
        claims.put("name","Tom");
        String jwt=Jwts.builder()
                .signWith(SignatureAlgorithm.HS256,"itheima")
                .setClaims(claims).setExpiration(new Date(System.currentTimeMillis())).compact();
        System.out.println(jwt);
    }

    @Test
    public void testparseJWt(){
        Claims claims=Jwts.parser().setSigningKey("itheima")
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoiVG9tIiwiaWQiOjEsImV4cCI6MTczMDQxNzI4Nn0.H7eoYBwQ_7NyhlhUSMrYfsE7G4LpMdwyGgd3Bg9KrHA")
                .getBody();
        System.out.println(claims);
    }

}
