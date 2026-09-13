namespace TicketsSoporte.logica
{
    public class FlujoTicket
    {
        private Dictionary<string, List<string>> dicTransiciones;

        public FlujoTicket()
        {
            dicTransiciones = new Dictionary<string, List<string>>
            {
                { "Abierto", new List<string> { "Asignado" } },
                { "Asignado", new List<string> { "Resuelto" } },
                { "Resuelto", new List<string> { "Cerrado" } }
            };
        }

        public bool puedeCambiarEstado(string strActual, string strDestino)
        {
            if (!dicTransiciones.ContainsKey(strActual))
            {
                return false;
            }

            return dicTransiciones[strActual].Contains(strDestino);
        }

        public void mostrarFlujo()
        {
            Console.ForegroundColor = ConsoleColor.Cyan;
            Console.WriteLine("\n=== FLUJO DE ESTADOS DEL TICKET ===");
            Console.ResetColor();
            Console.ForegroundColor = ConsoleColor.Green;
            Console.Write("  Abierto");
            Console.ResetColor();
            Console.Write(" -> ");
            Console.ForegroundColor = ConsoleColor.Yellow;
            Console.Write("Asignado");
            Console.ResetColor();
            Console.Write(" -> ");
            Console.ForegroundColor = ConsoleColor.Blue;
            Console.Write("Resuelto");
            Console.ResetColor();
            Console.Write(" -> ");
            Console.ForegroundColor = ConsoleColor.DarkGray;
            Console.Write("Cerrado");
            Console.ResetColor();
            Console.WriteLine();
        }
    }
}
