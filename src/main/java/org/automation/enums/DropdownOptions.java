package org.automation.enums;

/**
 * <h2>DropdownOptions — Training Notes: Enums for Test Data</h2>
 *
 * <p>An <strong>enum</strong> (enumeration) in Java is a special type that
 * holds a fixed set of named constants. Using an enum for dropdown option
 * values has major advantages over plain {@code String}s:</p>
 *
 * <h3>Why Enums instead of Strings?</h3>
 * <table border="1">
 *   <tr><th>String approach</th><th>Enum approach</th></tr>
 *   <tr>
 *     <td>{@code selectDropdown("Option 1")}</td>
 *     <td>{@code selectDropdown(DropdownOptions.OPTION_1)}</td>
 *   </tr>
 *   <tr>
 *     <td>Typo "Option1" compiles fine, fails at runtime</td>
 *     <td>Typo fails at compile time — caught immediately</td>
 *   </tr>
 *   <tr>
 *     <td>IDE can't autocomplete unknown strings</td>
 *     <td>IDE shows all valid options instantly</td>
 *   </tr>
 *   <tr>
 *     <td>Magic strings scattered across many test files</td>
 *     <td>One source of truth — change here, applies everywhere</td>
 *   </tr>
 * </table>
 *
 * <h3>Enum with Fields</h3>
 * <p>Each enum constant stores the exact string that appears in the HTML
 * {@code <option>} element's visible text. The {@code value} field provides
 * a typed, readable way to retrieve that string:
 * <pre>
 *   DropdownOptions.OPTION_1.getValue() // returns "Option 1"
 * </pre>
 * This value is passed to Selenium's {@code Select.selectByVisibleText()}.</p>
 *
 * <h3>Enum and switch</h3>
 * <p>Enums work perfectly with {@code switch} statements, making complex
 * branching logic based on dropdown choices clean and exhaustive-checkable
 * by the compiler.</p>
 */
public enum DropdownOptions {

    /**
     * The first dropdown option — visible text "Option 1".
     * Demonstrates how to encode the exact UI-facing text alongside
     * a meaningful Java constant name.
     */
    OPTION_1("Option 1"),

    /**
     * The second dropdown option — visible text "Option 2".
     */
    OPTION_2("Option 2");

    // ── Fields ────────────────────────────────────────────────────────────────

    /**
     * The visible text of the {@code <option>} HTML element as it appears
     * in the browser. Used by {@code Select.selectByVisibleText(value)}.
     */
    private final String value;

    // ── Constructor ────────────────────────────────────────────────────────────
    //
    // TRAINING NOTE: Enum constructors are always private (even without the
    // keyword). They are called once per constant when the enum class is loaded.

    /**
     * Associates a human-readable label with the enum constant.
     *
     * @param value The visible text of this dropdown option in the browser.
     */
    DropdownOptions(String value) {
        this.value = value;
    }

    // ── Accessor ──────────────────────────────────────────────────────────────

    /**
     * Returns the visible text value of this dropdown option.
     *
     * @return The option label as shown in the browser.
     */
    public String getValue() {
        return value;
    }
}
