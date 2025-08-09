package Lab15PartB;

import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Map;

@SpringBootApplication
public class Lab15PartBApplication implements CommandLineRunner {
	@Autowired
	private VectorStore vectorStore;

	public static void main(String[] args) {
		SpringApplication.run(Lab15PartBApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<Document> docs = List.of(
				doc("""
            Sunny 2BR apartment in downtown Madison. In-unit laundry, pet-friendly, 1 garage spot.
            Walk to cafes and lake. $2100/mo.
            """, Map.of("city","Madison","bedrooms",2,"type","apartment","price",2100)),

				doc("""
            Cozy 1BR cottage near Boulder trailheads. Backyard, EV charger, washer/dryer.
            Mountain views. $1800/mo.
            """, Map.of("city","Boulder","bedrooms",1,"type","cottage","price",1800)),

				doc("""
            Spacious 3BR house in Austin with fenced yard, updated kitchen, and home office.
            Great schools. $3200/mo.
            """, Map.of("city","Austin","bedrooms",3,"type","house","price",3200)),

				doc("""
            Modern studio in Seattle's Capitol Hill. Rooftop deck, gym access, dog-friendly.
            Light rail nearby. $1650/mo.
            """, Map.of("city","Seattle","bedrooms",0,"type","studio","price",1650)),

				doc("""
            4BR family home in Raleigh with two-car garage, big yard, and quiet cul-de-sac.
            Ideal for remote work. $2900/mo.
            """, Map.of("city","Raleigh","bedrooms",4,"type","house","price",2900)),

				doc("""
            2BR townhouse in Chicago's Lincoln Park. Fireplace, balcony, parking available.
            Close to park and zoo. $2500/mo.
            """, Map.of("city","Chicago","bedrooms",2,"type","townhouse","price",2500)),

				// --- more inventory ---
				doc("""
            Bright 1BR apartment in San Diego's North Park. A/C, dishwasher, shared courtyard.
            10 min to Balboa Park. $2200/mo.
            """, Map.of("city","San Diego","bedrooms",1,"type","apartment","price",2200)),

				doc("""
            Renovated 2BR condo in Denver LoDo. Garage parking, balcony, walk to Union Station.
            $2600/mo.
            """, Map.of("city","Denver","bedrooms",2,"type","condo","price",2600)),

				doc("""
            Charming studio in Boston Back Bay. Heat included, elevator building, laundry in basement.
            $2100/mo.
            """, Map.of("city","Boston","bedrooms",0,"type","studio","price",2100)),

				doc("""
            3BR ranch in Nashville with fenced yard, covered porch, and bonus room.
            Near East Nashville cafes. $2550/mo.
            """, Map.of("city","Nashville","bedrooms",3,"type","house","price",2550)),

				doc("""
            1BR loft in Minneapolis North Loop. Exposed brick, tall ceilings, indoor parking.
            $1900/mo.
            """, Map.of("city","Minneapolis","bedrooms",1,"type","loft","price",1900)),

				doc("""
            2BR duplex in Portland (Alberta Arts). Backyard, bike storage, cats OK.
            $2300/mo.
            """, Map.of("city","Portland","bedrooms",2,"type","duplex","price",2300)),

				doc("""
            4BR suburban house in Plano with community pool access and top schools.
            $3100/mo.
            """, Map.of("city","Plano","bedrooms",4,"type","house","price",3100)),

				doc("""
            1BR carriage house in Charleston historic district. Private patio, off-street parking.
            $2000/mo.
            """, Map.of("city","Charleston","bedrooms",1,"type","carriage_house","price",2000)),

				doc("""
            Spacious 2BR apartment in Phoenix Midtown. Pool, covered parking, near light rail.
            $1850/mo.
            """, Map.of("city","Phoenix","bedrooms",2,"type","apartment","price",1850)),

				doc("""
            3BR townhouse in Jersey City Heights. Roof deck skyline views, W/D in unit.
            PATH bus nearby. $3400/mo.
            """, Map.of("city","Jersey City","bedrooms",3,"type","townhouse","price",3400)),

				doc("""
            1BR apartment in Atlanta Old Fourth Ward. BeltLine access, gym, dog park.
            $1750/mo.
            """, Map.of("city","Atlanta","bedrooms",1,"type","apartment","price",1750)),

				doc("""
            Modern 2BR condo in Miami Brickell. Pool, concierge, walk to restaurants.
            $3200/mo.
            """, Map.of("city","Miami","bedrooms",2,"type","condo","price",3200)),

				doc("""
            3BR house in Columbus Clintonville. Finished basement, garage, near parks.
            $2200/mo.
            """, Map.of("city","Columbus","bedrooms",3,"type","house","price",2200)),

				doc("""
            Cozy studio in Salt Lake City Sugar House. Mountain access, on-site laundry.
            $1350/mo.
            """, Map.of("city","Salt Lake City","bedrooms",0,"type","studio","price",1350)),

				doc("""
            2BR apartment in Philadelphia Fishtown. Exposed beams, roof deck, near breweries.
            $2000/mo.
            """, Map.of("city","Philadelphia","bedrooms",2,"type","apartment","price",2000)),

				doc("""
            4BR home in San Jose Willow Glen. EV charger, large kitchen, quiet street.
            $4200/mo.
            """, Map.of("city","San Jose","bedrooms",4,"type","house","price",4200)),

				doc("""
            1BR casita in Tucson near U of A. Private entry, mini-split A/C, shaded patio.
            $1200/mo.
            """, Map.of("city","Tucson","bedrooms",1,"type","casita","price",1200)),

				doc("""
            2BR lakefront cabin in Duluth. Fireplace, deck, kayaking access in summer.
            $1700/mo.
            """, Map.of("city","Duluth","bedrooms",2,"type","cabin","price",1700))
		);

		vectorStore.add(docs);
		System.out.println("Loaded " + docs.size() + " rental docs into Redis vector store.");
	}

	private static Document doc(String text, Map<String, Object> meta) {
		Document d = new Document(text.strip());
		d.getMetadata().putAll(meta);
		return d;
	}
}
