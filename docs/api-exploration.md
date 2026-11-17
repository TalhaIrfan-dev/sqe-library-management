# API Exploration

## 1. Create Book

**Method:** POST  
**Path:** `/books`

**Request Body:**
```json
{
  "title": "Clean Code",
  "isbn": "1234567890123",
  "author": "Robert Martin",
  "totalCopies": 3
}
```

**Expected Status:** 201

**Response:**
```json
{
  "title": "Clean Code",
  "isbn": "1234567890123",
  "author": "Robert Martin",
  "availableCopies": 3
}
```

**Result:** Passed.

---

## 2. Get All Books

**Method:** GET  
**Path:** `/books`

**Status:** 200

**Response:**
```json
[
  {
    "title": "Clean Code",
    "isbn": "1234567890123",
    "author": "Robert Martin",
    "availableCopies": 3
  }
]
```

**Result:** Passed.

---

## 3. Get Book by ISBN

**Method:** GET  
**Path:** `/books/1234567890123`

**Status:** 200

**Response:**
```json
{
  "title": "Clean Code",
  "isbn": "1234567890123",
  "author": "Robert Martin",
  "availableCopies": 3
}
```

**Result:** Passed.

---

## 4. Borrow Book

**Method:** POST  
**Path:** `/members/501/borrow`

**Request Body:**
```json
{
  "isbn": "1234567890123"
}
```

**Status:** 200

**Response:**
```json
{
  "isbn": "1234567890123",
  "availableCopies": 2
}
```

**Result:** Passed.

---

## 5. Return Book

**Method:** POST  
**Path:** `/members/501/return`

**Request Body:**
```json
{
  "isbn": "1234567890123"
}
```

**Status:** 200

**Response:**
```json
{
  "isbn": "1234567890123",
  "availableCopies": 3
}
```

**Result:** Passed.

---

## 6. Get Fine Tier

**Method:** GET  
**Path:** `/members/501/fines?daysOverdue=15`

**Status:** 200

**Response:**
```json
{
  "memberId": 501,
  "daysOverdue": 15,
  "tier": "High"
}
```

**Result:** Passed.

---

## 7. Invalid Request — Missing Title

**Method:** POST  
**Path:** `/books`

**Request Body:**
```json
{
  "isbn": "9999999999999",
  "author": "Test Author",
  "totalCopies": 1
}
```

**Status:** 400

**Observed Response:**
```json
{
  "timestamp": "<dynamic timestamp>",
  "status": 400,
  "error": "Bad Request",
  "path": "/books"
}
```

**Observation:** The status code is correct, but the response does not follow the API contract's required `{"error":"message"}` format.

---

## 8. Invalid Request — Wrong Type

**Method:** POST  
**Path:** `/books`

**Request Body:**
```json
{
  "title": "Wrong Type Book",
  "isbn": "8888888888888",
  "author": "Test Author",
  "totalCopies": "three"
}
```

**Status:** 400

**Observed Response:**
```json
{
  "timestamp": "<dynamic timestamp>",
  "status": 400,
  "error": "Bad Request",
  "path": "/books"
}
```

**Observation:** The wrong type is correctly rejected with status 400, but the response does not follow the API contract's required `{"error":"message"}` format.