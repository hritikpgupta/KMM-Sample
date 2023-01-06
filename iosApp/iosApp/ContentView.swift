import SwiftUI
import shared

struct ContentView: View {
    let greet = Greeting().greet()
    
    @ObservedObject private(set) var viewModel : ViewModel
    
    var body: some View {
        Text(viewModel.text)
    }
}

extension ContentView{
    
    class ViewModel: ObservableObject {
        @Published var text = "Loading..."
        init() {
            Greeting().greetings { g , e in
                DispatchQueue.main.async {
                    if let greeting = g {
                        self.text = g!
                    } else {
                        self.text = e?.localizedDescription ?? "error"
                    }
                }
            }
        }   
    }
}

