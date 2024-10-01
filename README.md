# Persona

**Persona** is a proof-of-concept project that addresses a common issue: how do you maintain up-to-date information (like your email or name) across different services? Imagine being able to update your contact details in one place, and have it reflected automatically everywhere it's used.

Persona allows you to store your information in a profile, where external services reference the profile's _keys_ rather than copying the data itself. When you update a value in your persona profile, the change is propagated to all linked services.

## Problem Statement

For instance, let's consider a common scenario:

### Example Use Case

Imagine a form on a City Hall's website to submit complaints without requiring registration:

- **citizen_name** (input)
- **citizen_email** (input)
- **complaint** (textarea)

Once you submit the form, these fields are registered with an `#issueId`.

Now, what if you change your name or email? How do you update your information so that you can still be notified about the issue?

This is where **Persona** comes in.

### Persona Solution

Persona allows a service (e.g., City Hall) to register form fields with a _key_ from your Persona profile, rather than the actual content.

Imagine the `example01` profile in the **Persona** website:

| Key    | Value              |
|--------|--------------------|
| name   | William             |
| dob    | 01-01-1970          |
| email  | will@persona.com    |

Each of these keys can be mapped to a URI (e.g., `persona://persona.com/example01/email`).

Instead of registering your email directly with the form, the City Hall uses the persona _URI_. When you change your email on your Persona profile, a notification is sent to the City Hall service, which updates its records automatically.

## Project Structure

Persona consists of two main components:

### 1. Business Model

The business model serves as the core of the Persona system. It includes:

- A Spring server for managing user authentication and CRUD operations on profile properties.
- HTML templates for user interaction.
- API controllers to handle communication with third-party services.
- Support for OAUTH to allow third-party services to bind to user profiles.

Additionally, the project uses **Liquibase** to manage database schema updates, which is maintained as a separate subproject (No need those transient dependencies at runtime!).

### 2. Third-Party Libraries and Services

Persona provides tools for third-party services to integrate seamlessly. The main components include:

#### Libraries

- **persona.js**: A JavaScript library for integrating Persona with web forms. Ideally, this is the only script a service needs to add to enable integration.
- **persona.css**: The look and field of the inputs being highlighted

#### Services

- **Man-in-the-middle**: This service acts as a proxy between a third-party service and Persona. It intercepts POST requests containing Persona URIs and replaces them with the actual data from the Persona profile before passing the request along.

- **Binding**:
    - Provides CRUD operations on domain keys.
        - create `issue777.citizen_email` bound to _persona://persona.com/example01/email_
        - update _persona://persona.com/example01/email_ to _new_email@persona.com_
    - Communicates with both the man-in-the-middle service and Persona's internal services (no one else).
    - Contains a separate `liquibase` project for its own schema updates.

- **Domain Sync**:
    - This service handles domain event updates (e.g., updating a user's email in a City Hall complaint form: changed `issue777.citizen_email` to _new_email@persona.com_).
    - It is called (only) by the binding service and should be implemented by the Third Party.

## Conclusion

Persona is designed to streamline the management of personal data across multiple services by allowing third parties to bind to a user's profile keys. This approach eliminates the need for users to manually update their information in multiple places, while ensuring data integrity and privacy.
