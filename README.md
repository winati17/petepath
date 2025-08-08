# PetePath

**Description**  
PetePath is a mobile application designed to modernize traditional public transportation (Pete-Pete) by providing users with digital route information, safety features, and journey tracking.  
All features are presented through key app screens and UI interactions shown below.

---

## Project Overview
- **Problem Statements**
  - High cost of online ride-hailing services.
  - Lack of digital route information for Pete-Pete.
  - Insufficient modernization of public transport.
  - Concerns about the safety and comfort of conventional transport.

- **What PetePath Offers**
  1. Provides easy access to route information.
  2. Promotes digitization of traditional transportation.
  3. Enhances user safety.

---

## App Features
- View Pete-Pete routes.
- Report driver misconduct or other issues.
- View journey history and submitted reports.

---

## App Screens (UI Overview)
- **App Logo & Splash Screen**  
  Displays the app’s branding and launches the user interface.

- **Authentication**  
  - **Sign Up Screen** — username, email validation, password field.  
  - **Error States** — cases like invalid email formats, account not registered, or username/email already in use.

- **Main Screens**  
  - **Homepage & Route Selection** — lists available routes like “Route 02 – Unhas–Veteran” and others.  
  - **Route Detail** — displays stops along the selected Pete-Pete route.  
  - **Updated Homepage** — highlights the most recently viewed route.

- **History & Reports**  
  - **Activity History** — shows past activity; option to add new activities.  
  - **Report History** — shows past reports; includes forms to submit new reports (route selection, issue category, description, vehicle plate).  
  - **Profile** — displays user username and email; options to log out, view accounts, and delete history or account.

---

## Installation & Environment Setup
1. Clone the repository:
   ```bash
   git clone https://github.com/winati17/petepath.git
   cd petepath
   ```
2. Open the project in Android Studio.
3. Sync Gradle to resolve dependencies.
4. Run the app on an emulator or physical device.

## Usage Flow
1. Launch the app via splash screen.
2. Sign up or log in.
3. Browse Pete-Pete routes and view route details.
4. Optionally report issues during a journey.
5. View your activity or report history.
6. Manage your profile or account settings.
