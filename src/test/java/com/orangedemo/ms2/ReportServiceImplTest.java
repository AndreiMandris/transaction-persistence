package com.orangedemo.ms2;

import com.orangedemo.ms2.dao.TransactionDao;
import com.orangedemo.ms2.dto.FullReport;
import com.orangedemo.ms2.dto.ReportLine;
import com.orangedemo.ms2.model.Transaction;
import com.orangedemo.ms2.model.TransactionType;
import com.orangedemo.ms2.service.ReportService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ReportServiceImplTest {

    @MockBean
    TransactionDao transactionDao;

    @Autowired
    ReportService reportService;

    @Before
    public void mockGetAllByCnp() {
        Mockito.when(transactionDao.getAllByCnp("1901223211419")).thenReturn(
                Arrays.asList(
                        new Transaction(new BigInteger("0"), TransactionType.WALLET_TO_WALLET, "RO09BCYP0000001234567890",
                                "1901223211419", "Marius Popa", "car payment", new BigDecimal(2500)),
                        new Transaction(new BigInteger("1"), TransactionType.IBAN_TO_IBAN, "RO09BCYP0000001234567891",
                                "1901223211419", "Marius Popa", "house repairs", new BigDecimal(4500)),
                        new Transaction(new BigInteger("2"), TransactionType.IBAN_TO_IBAN, "RO09BCYP0000001234567890",
                                "1901223211419", "Marius Popa", "cleaning", new BigDecimal(500)),
                        new Transaction(new BigInteger("3"), TransactionType.WALLET_TO_IBAN, "RO09BCYP0000001234567890",
                                "1901223211419", "Marius Popa", "gardening", new BigDecimal(700)),
                        new Transaction(new BigInteger("4"), TransactionType.IBAN_TO_IBAN, "RO09BCYP0000001234567890",
                                "1901223211419", "Marius Popa", "car payment 2", new BigDecimal(3500))
                )
        );
    }

    @Test
    public void testGetReportByCnp(){
        Map<TransactionType, BigDecimal> sums = new HashMap<>();
        sums.put(TransactionType.IBAN_TO_IBAN, new BigDecimal(4500));
        sums.put(TransactionType.IBAN_TO_WALLET, new BigDecimal(0));
        sums.put(TransactionType.WALLET_TO_IBAN, new BigDecimal(0));
        sums.put(TransactionType.WALLET_TO_WALLET, new BigDecimal(0));

        ReportLine expectedReportLine1 = new ReportLine.ReportBuilder()
                .withCnp("1901223211419")
                .withIban("RO09BCYP0000001234567891")
                .withNoOfTransactions(1)
                .withSums(sums)
                .build();

        sums.put(TransactionType.IBAN_TO_IBAN, new BigDecimal(4000));
        sums.put(TransactionType.IBAN_TO_WALLET, new BigDecimal(0));
        sums.put(TransactionType.WALLET_TO_IBAN, new BigDecimal(700));
        sums.put(TransactionType.WALLET_TO_WALLET, new BigDecimal(2500));

        ReportLine expectedReportLine2 = new ReportLine.ReportBuilder()
                .withCnp("1901223211419")
                .withIban("RO09BCYP0000001234567890")
                .withNoOfTransactions(4)
                .withSums(sums)
                .build();

        FullReport expectedFullReport = new FullReport(
                Arrays.asList(expectedReportLine1, expectedReportLine2)
        );

        Assert.assertEquals(expectedFullReport, reportService.getReportByCnp("1901223211419"));
    }
}
