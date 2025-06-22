# travel_search.py
# Simple menu-based travel search app based on destination or budget

# Sample destination data stored in a list of dictionaries
travel_data = [
    {"destination": "Amalfi Coast", "country": "Italy", "cost": 1800},
    {"destination": "Santorini", "country": "Greece", "cost": 2000},
    {"destination": "Tokyo", "country": "Japan", "cost": 2200},
    {"destination": "Paris", "country": "France", "cost": 1600},
    {"destination": "New York City", "country": "USA", "cost": 1500}
]

# Displays the main menu options to the user
def show_menu():
    print("\n=== Travel Search Menu ===")
    print("1. Search by destination")
    print("2. Search by budget")
    print("3. Exit")

# Handles destination search based on user input
def search_by_destination():
    name = input("Enter destination name: ").strip().lower()
    
    # Filters destinations that match the input
    results = [place for place in travel_data if name in place["destination"].lower()]
    
    if results:
        print("\nMatching destinations:")
        for r in results:
            print(f"{r['destination']} ({r['country']}) - ${r['cost']}")
    else:
        print("No results found.")

# Handles budget-based search for destinations within user’s price range
def search_by_budget():
    try:
        budget = int(input("Enter your maximum budget: "))
    except ValueError:
        print("Please enter a valid number.")
        return

    # Filters destinations that are equal to or below budget
    results = [place for place in travel_data if place["cost"] <= budget]
    
    if results:
        print("\nDestinations within your budget:")
        for r in results:
            print(f"{r['destination']} ({r['country']}) - ${r['cost']}")
    else:
        print("No destinations found within budget.")

# Main program loop
def main():
    while True:
        show_menu()
        choice = input("Choose an option (1-3): ")

        if choice == "1":
            search_by_destination()
        elif choice == "2":
            search_by_budget()
        elif choice == "3":
            print("Exiting... Goodbye!")
            break
        else:
            print("Invalid choice. Try again.")

# Entry point for the program
if __name__ == "__main__":
    main()

