package com.punit.transactions;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.punit.transactions.controller.TransactionsController;
import com.punit.transactions.model.Transaction;
import com.punit.transactions.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.*;


class TransactionsApplicationTests {

	@Mock
	private TransactionService transactionService;

	@InjectMocks
	private TransactionsController transactionsController;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testGetTransactions() {
		// Create mock transactions
		Transaction successTransaction1 = new Transaction(556666678, "success", 500, LocalDateTime.now());
		Transaction failedTransaction1 = new Transaction(556666678, "failed", 200, LocalDateTime.now());
		Transaction pendingTransaction1 = new Transaction(556666678, "pending", 100, LocalDateTime.now());

		// Create a mock response map
		Map<String, Object> mockResponse = new HashMap<>();
		mockResponse.put("success", Arrays.asList(successTransaction1));
		mockResponse.put("failed", Arrays.asList(failedTransaction1));
		mockResponse.put("pending", Arrays.asList(pendingTransaction1));

		// Set up the behavior of the transactionService mock
		doReturn(mockResponse).when(transactionService).getAllTransactions(556666678);

		// Call the method under test
		Object response = transactionsController.getTransactionsByStatus(556666678, "all");

		// Verify the results
		assertTrue(response instanceof Map);
		Map<String, Object> responseMap = (Map<String, Object>) response;

		assertEquals(mockResponse.get("success"), responseMap.get("success"));
		assertEquals(mockResponse.get("failed"), responseMap.get("failed"));
		assertEquals(mockResponse.get("pending"), responseMap.get("pending"));

		verify(transactionService).getAllTransactions(556666678);
	}

	@Test
	void testGetTransactions_All() {
		// Create mock transactions
		Transaction successTransaction1 = new Transaction(556666678, "success", 500, LocalDateTime.now());
		Transaction successTransaction2 = new Transaction(556666678, "success", 300, LocalDateTime.now());

		// Create a mock response map
		Map<String, Object> mockResponse = new HashMap<>();
		mockResponse.put("success", Arrays.asList(successTransaction1, successTransaction2));
		mockResponse.put("failed", Collections.emptyMap());
		mockResponse.put("pending", Collections.emptyMap());

		// Set up the behavior of the transactionService mock
		when(transactionService.getAllTransactions(556666678)).thenReturn(mockResponse);

		// Call the method under test
		Object response = transactionsController.getTransactionsByStatus(556666678, "all");

		// Verify the results
		assertTrue(response instanceof Map);
		Map<String, Object> responseMap = (Map<String, Object>) response;

		assertEquals(mockResponse.get("success"), responseMap.get("success"));
		assertTrue(((Map<?, ?>) responseMap.get("failed")).isEmpty());
		assertTrue(((Map<?, ?>) responseMap.get("pending")).isEmpty());

		// Verify that getAllTransactions was called with the correct arguments
		verify(transactionService).getAllTransactions(556666678);
	}

	@Test
	void testGetTransactions_Success() {
		// Create mock transactions
		Transaction successTransaction1 = new Transaction(556666678, "success", 500, LocalDateTime.now());
		Transaction successTransaction2 = new Transaction(556666678, "success", 300, LocalDateTime.now());

		// Create a mock response map with success transactions
		Map<String, Object> mockResponse = new HashMap<>();
		mockResponse.put("success", Arrays.asList(successTransaction1, successTransaction2));
		mockResponse.put("failed", "Server not responding"); // Empty list for "failed"
		mockResponse.put("pending", "Server not responding"); // Empty list for "pending"

		// Set up the behavior of the transactionService mock
		when(transactionService.getAllTransactions(556666678)).thenReturn(mockResponse);

		// Call the method under test
		Object response = transactionsController.getTransactionsByStatus(556666678,"all");

		// Verify the results
		assertTrue(response instanceof Map);
		Map<String, Object> responseMap = (Map<String, Object>) response;

		// Ensure that the "success" key exists and contains a list of transactions
		assertTrue(responseMap.containsKey("failed"));
		assertEquals("Server not responding", responseMap.get("failed"));
		assertTrue(responseMap.containsKey("pending"));
		assertEquals("Server not responding", responseMap.get("pending"));

		// Verify that getAllTransactions was called with the correct arguments
		verify(transactionService).getAllTransactions(556666678);
	}

	@Test
	void testGetTransactions_Failed() {
		// Create a mock response with only failed transactions
		Transaction failedTransaction1 = new Transaction(556666678, "failed", 200, LocalDateTime.now());
		Transaction failedTransaction2 = new Transaction(556666678, "failed", 100, LocalDateTime.now());

		Map<String, Object> mockResponse = new HashMap<>();
		mockResponse.put("success", "Server not responding");
		mockResponse.put("failed", Arrays.asList(failedTransaction1, failedTransaction2));
		mockResponse.put("pending", "Server not responding");

		when(transactionService.getTransactions(556666678, "failed")).thenReturn(mockResponse);

		// Call the method under test
		Object response = transactionsController.getTransactionsByStatus(556666678, "failed");

		// Verify the results
		assertTrue(response instanceof Map);
		Map<String, Object> responseMap = (Map<String, Object>) response;

		assertTrue(responseMap.containsKey("success"));
		assertEquals("Server not responding", responseMap.get("success"));
		assertEquals(mockResponse.get("failed"), responseMap.get("failed"));
		assertTrue(responseMap.containsKey("pending"));
		assertEquals("Server not responding", responseMap.get("pending"));

		// Verify that getAllTransactions was called with the correct arguments
		verify(transactionService).getTransactions(556666678, "failed");
	}



	@Test
	void testGetTransactions_EmptyResponse() {
		// Create an empty mock response
		Map<String, Object> mockResponse = new HashMap<>();
		mockResponse.put("success", Collections.emptyMap());
		mockResponse.put("failed", Collections.emptyMap());
		mockResponse.put("pending", Collections.emptyMap());

		when(transactionService.getAllTransactions(556666678)).thenReturn(mockResponse);

		// Call the method under test
		Object response = transactionsController.getTransactionsByStatus(556666678, "all");

		// Verify the results
		assertTrue(response instanceof Map);
		Map<String, Object> responseMap = (Map<String, Object>) response;

		assertTrue(((Map<?, ?>) responseMap.get("success")).isEmpty());
		assertTrue(((Map<?, ?>) responseMap.get("failed")).isEmpty());
		assertTrue(((Map<?, ?>) responseMap.get("pending")).isEmpty());

		// Verify that getAllTransactions was called with the correct arguments
		verify(transactionService).getAllTransactions(556666678);
	}

	@Test
	void testGetTransactions_SeverBusyResponse() {
		// Create an empty mock response
		Map<String, Object> mockResponse = new HashMap<>();
		mockResponse.put("success", "Server not responding");
		mockResponse.put("failed", "Server not responding");
		mockResponse.put("pending", "Server not responding");

		when(transactionService.getAllTransactions(556666678)).thenReturn(mockResponse);

		// Call the method under test
		Object response = transactionsController.getTransactionsByStatus(556666678, "all");

		// Verify the results
		assertTrue(response instanceof Map);
		Map<String, Object> responseMap = (Map<String, Object>) response;

		assertEquals("Server not responding", responseMap.get("success"));
		assertEquals("Server not responding", responseMap.get("pending"));
		assertEquals("Server not responding", responseMap.get("failed"));

		// Verify that getAllTransactions was called with the correct arguments
		verify(transactionService).getAllTransactions(556666678);
	}

}
