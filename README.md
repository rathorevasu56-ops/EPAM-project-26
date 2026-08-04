# EPAM-project-26

<div align="center">

# 🪔 XYZ Bank — Diwali Bonanza Coupon

**A JavaScript case study: eligibility checks, DOM-driven UI, and client-side persistence — all in one dependency-free HTML file.**

[![Made with](https://img.shields.io/badge/Made%20with-HTML%2FCSS%2FJS-1c2027?style=flat-square)](#)
[![No dependencies](https://img.shields.io/badge/Dependencies-None-c9971f?style=flat-square)](#)
[![Browser support](https://img.shields.io/badge/Tested%20on-IE%20%7C%20Chrome%20%7C%20Firefox-0f2a4a?style=flat-square)](#)
[![License](https://img.shields.io/badge/License-MIT-1f7a4d?style=flat-square)](#-license)

</div>

---

## 📖 Overview

XYZ Bank is running a **Diwali Bonanza offer**: existing customers can log
in with their customer ID, register for the offer, and receive a unique
coupon code to use on further shopping. This repo is a self-contained
case study implementing that flow in **plain JavaScript** — no frameworks,
no bundler, no npm install. Open the HTML file and it just works.

It was built to demonstrate three specific scenarios, plus two extras
added along the way:

- ✅ A **welcome message** rendered dynamically with JavaScript
- ✅ A **Register** button that reveals/hides step-by-step instructions
- ✅ A **coupon code** generated from the customer's own ID
- ✅ An **eligibility rule** so the offer isn't available to just anyone
- ✅ **Persistence** via `localStorage` so coupons survive a page refresh

---

## 🗂️ Table of contents

- [Features](#-features)
- [Preview](#-preview)
- [Eligibility rule](#-eligibility-rule)
- [How the coupon flow works](#-how-the-coupon-flow-works)
- [Project structure](#-project-structure)
- [Getting started](#-getting-started)
- [Function reference](#-function-reference)
- [Roadmap](#-roadmap)
- [License](#-license)

---

## ✨ Features

| Scenario | Description | Implementation |
|---|---|---|
| 1️⃣ Welcome message | Greets the customer (or explains why they aren't recognised), written entirely via JS | `displayWelcomeMessage()` |
| 2️⃣ Register & reveal steps | Clicking **Register** toggles a panel of numbered instructions | `toggleStepsPanel()` |
| 3️⃣ Coupon generation | Builds a unique code from the customer's ID | `generateCouponCode()` / `hashCustomerId()` |
| 🔒 Eligibility check | Only IDs matching a bank-defined rule can register | `isExistingCustomer()` |
| 💾 Persistence | One coupon per customer, saved across visits | `localStorage` helpers |

---

## 🖼 Preview

> Add a screenshot or GIF of the page here once you have one, e.g.
>
> ```md
> ![App preview](./screenshot.png)
> ```

**Suggested flow to capture for a screenshot/GIF:**
1. Land on the page → welcome message shown
2. Type an eligible customer ID → message updates, "Register" enabled
3. Click **Register** → steps panel opens
4. Click **Generate my coupon** → coupon ticket appears

---

## 🔑 Eligibility rule

```js
var ELIGIBILITY_MARKER = "CUST9"; // existing customer IDs must contain this substring
var MIN_ID_LENGTH = 6;            // existing customer IDs are at least this long
```

Any customer ID that is **at least 6 characters long** and **contains the
substring `CUST9`** anywhere in it is treated as an existing customer —
for example `CUST9021`, `MYCUST9X`, and `ACUST9481` all qualify. IDs
without that marker (e.g. `CUST1092`) are politely rejected with an
explanation on screen.

> In a production system, `isExistingCustomer()` would call the bank's
> core API instead of doing a client-side string check — this is a
> stand-in for that lookup, kept simple so the demo works offline.

**Try it yourself:**

| Customer ID | Result |
|---|---|
| `CUST9021` | ✅ Eligible |
| `ACUST9481` | ✅ Eligible |
| `CUST1092` | ❌ Not eligible |

---

## 🎟 How the coupon flow works

```
Customer types ID
        │
        ▼
displayWelcomeMessage()  ──▶  greets or explains rejection
        │
        ▼
Click "Register" ──▶ toggleStepsPanel()
        │                     │
        │              checks eligibility
        │                     │
        ▼                     ▼
   steps panel opens   (rejected → error shown)
        │
        ▼
Click "Generate my coupon" ──▶ generateCouponCode()
        │
        ├── already has a saved coupon? ──▶ show existing code
        │
        └── otherwise: hash ID → build code → save to localStorage
                             │
                             ▼
                    showCouponTicket(code, issuedAt)
```

Coupons are saved under the `xyzBankDiwaliCoupons` key in `localStorage`,
keyed by customer ID — so re-entering the same ID later (even after a
refresh) reopens the steps panel and shows the **original** coupon
instead of generating a new one. One coupon per customer, guaranteed
client-side.

All storage calls are wrapped in `try/catch`, since some browsers (e.g.
private/incognito windows) can throw on `localStorage` access instead of
failing quietly.

---

## 📁 Project structure

```
.
├── xyz-bank-diwali-coupons.html   # everything — markup, styles, and script in one file
└── README.md                      # you are here
```

Kept deliberately as a single file so it can be opened directly in a
browser or dropped into any static host with zero build step.

---

## 🚀 Getting started

No install required.

```bash
git clone https://github.com/<your-username>/<your-repo>.git
cd <your-repo>
open xyz-bank-diwali-coupons.html   # or just double-click it
```

Tested for compatibility across **IE, Chrome, and Firefox** — the script
intentionally sticks to `var` and string concatenation rather than
`const`/`let`, arrow functions, or template literals.

**Reset the demo data** (clear all saved coupons) by opening the browser
console on the page and running:

```js
localStorage.removeItem("xyzBankDiwaliCoupons");
```

---

## 🧩 Function reference

| Function | Purpose |
|---|---|
| `isExistingCustomer(id)` | Applies the `CUST9` eligibility rule |
| `displayWelcomeMessage(id)` | Scenario 1 — writes the welcome/rejection message |
| `updateCustomerChip(id)` | Keeps the header's "signed in as" chip in sync |
| `toggleStepsPanel()` | Scenario 2 — shows/hides the registration steps |
| `hashCustomerId(id)` | Turns a customer ID into a 5-digit numeric hash |
| `generateCouponCode()` | Scenario 3 — builds and saves a coupon code |
| `showCouponTicket(code, issuedAt)` | Renders the coupon ticket UI |
| `readCouponStore()` / `saveCouponRecord()` / `getCouponRecord()` | `localStorage` read/write helpers |
| `checkForReturningCustomer(id)` | Auto-opens a previously issued coupon on return visits |

---

## 🛣 Roadmap

- [ ] Replace `isExistingCustomer()` with a real backend/API call
- [ ] Generate and validate coupon codes server-side (client-side codes
      shouldn't be trusted in production)
- [ ] Add real expiry logic (compare `registeredAt` to a cutoff date
      instead of a static label)
- [ ] Add a screenshot/GIF to the [Preview](#-preview) section

---

## 📄 License

This project is a case study created for educational purposes. Feel free
to fork, adapt, and reuse it.

<div align="center">

Built as part of a JavaScript case-study course · XYZ Bank is a fictional
brand used for demonstration only.

</div>